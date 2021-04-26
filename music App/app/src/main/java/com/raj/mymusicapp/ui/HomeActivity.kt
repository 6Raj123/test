package com.raj.mymusicapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raj.mymusicapp.R
import com.raj.mymusicapp.adapter.MusicListAdapter
import com.raj.mymusicapp.di.DaggerRetrofitComponent
import com.raj.mymusicapp.di.RetrofitComponent
import com.raj.mymusicapp.di.RetrofitModule
import com.raj.mymusicapp.model.DisplayMusicList
import com.raj.mymusicapp.model.MusicList
import com.raj.mymusicapp.network.CallBackMainActivity
import com.raj.mymusicapp.network.NetworkCheck
import com.raj.mymusicapp.network.RetrofitApi
import retrofit2.Retrofit
import javax.inject.Inject

class HomeActivity : BaseActivity(), CallBackMainActivity {


    @set:Inject
    var retrofit: Retrofit? = null
    private lateinit var networkCheck: NetworkCheck
    private lateinit var homeViewModel: HomeActivityViewModel
    private lateinit var  searchItem : EditText
    private lateinit var  selectedCategoryTextView : TextView
    private lateinit var selectedCategory : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchItem  = findViewById(R.id.searchItemEditView)
        selectedCategoryTextView = findViewById(R.id.selectedCategory)
        val category = resources.getStringArray(R.array.Category)
        val spinner = findViewById<Spinner>(R.id.spinner)

        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, category
            )
            spinner.adapter = adapter


            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {

                    selectedCategory = category[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }

        }
        searchItem.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    callApiForSearchItem()
                    true
                }
                else -> false
            }
        }

        homeViewModel =
            ViewModelProvider(this)[HomeActivityViewModel::class.java]


        val retrofitComponent: RetrofitComponent =
            DaggerRetrofitComponent.builder().retrofitModule(RetrofitModule())
                .build()
        retrofitComponent.inject(this)



        val musicListObserver = Observer<List<DisplayMusicList>> { displayMusicLists ->
            if (displayMusicLists.isEmpty())
                Toast.makeText(this@HomeActivity, "No Data Found", Toast.LENGTH_LONG).show()
            else
            showMusicList(displayMusicLists)
        }
        homeViewModel.displayMusicLists.observe(this, musicListObserver)
        networkCheck = NetworkCheck()

    }

    fun searchButtonClicked(view : View?)
    {
        callApiForSearchItem()
    }


    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onSuccessResponse(musicList: MusicList) {
        searchItem.setText("")
        selectedCategoryTextView.setText(selectedCategory)
        homeViewModel.convertToDisplayData(musicList)
        dialog?.dismiss()
    }

    override fun onFailureResponse() {
        Log.d("TAG", "Failure response")
        dialog?.dismiss()
        Toast.makeText(this@HomeActivity, "Failed to connect to Server", Toast.LENGTH_LONG)
            .show()
    }

    private fun callApiForSearchItem()
    {
        if( homeViewModel.isCategoryNotSelected(selectedCategory, getString(R.string.select_category)))
        {
            Toast.makeText(this@HomeActivity, "Please select category", Toast.LENGTH_SHORT)
                .show()
        }else if( homeViewModel.isSearchItemEmpty(searchItem.text.toString()))
        {
            Toast.makeText(this@HomeActivity, "Please enter search item", Toast.LENGTH_SHORT)
                .show()
        }else if(networkCheck.isInternetAvailable(this@HomeActivity)){
            searchItem.hideKeyboard()
            showProgress()
            RetrofitApi.getMusicListDataFromServer(retrofit,this, selectedCategory,searchItem.text.toString())
        } else {
            Toast.makeText(this@HomeActivity, "Please check your internet connection", Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun showMusicList(musicLists: List<DisplayMusicList>) {

        //getting recyclerview from xml
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)


        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity, RecyclerView.VERTICAL, false)


        //creating our adapter
        val adapter = MusicListAdapter(musicLists)

        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

    }

}