package com.example.architecturecomponents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.architecturecomponents.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLifecycle()
        initViewModel()

        binding.viewModelButton.setOnClickListener {
            binding.message1TextView.text = (viewModel.contador++).toString()
        }

        binding.liveDataButton.setOnClickListener {

        }
    }

    fun initLifecycle(){
        lifecycle.addObserver(LifeCycleObserverClass(this))
    }

    fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ViewModelClass::class.java)
    }
}