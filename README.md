# Architecture Components

## Dependencies: 

[I get from here de dependencies (maybe is outdated now)](https://developer.android.com/jetpack/androidx/releases/lifecycle)

```
    dependencies {
        def lifecycle_version = "2.3.1"

        // ViewModel
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
        implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'        
        // LiveData
        implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
        // Lifecycles only (without ViewModel or LiveData)
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version")


    }
    
```

Implement View Binding:

```
 buildFeatures {
        viewBinding = true
    }
```

## Lifecycle:


📄 LifeCycleObserverClass:

```
class LifeCycleObserverClass(var activity: Activity) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateListener() {
        Log.e("TAG", "==>> On Create")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResumeListener() {
        Log.e("TAG", "==>> On Resume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPauseListener() {
        Log.e("TAG", "==>> On Pause")
    }
}
```

📄 MainActivity:

```
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLifecycle()
        binding.liveDataButton.setOnClickListener {
            
        }
    }

    fun initLifecycle(){
        lifecycle.addObserver(LifeCycleObserverClass(this))
    }
}
```

## ViewModel:


### Dependencies for ViewModel:

```
implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'
```

📄 ViewModelClass:

```
class ViewModelClass: ViewModel() {
    var contador: Int = 0
}
```

📄 MainActivity:

```
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
```




## LiveData:

```
    fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ViewModelClass::class.java)
        // Make Observer:
        val nameObserver = Observer<String> {
            binding.message2TextView.text = it.toString()
        }
        // assig observer:
        viewModel.getCurrentName().observe(this, nameObserver)
    }
```

📄 MainActivity:

```
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
            binding.message1TextView.text = (++viewModel.contador).toString()
        }

        binding.liveDataButton.setOnClickListener {
            viewModel.getCurrentName().value = "Hola amigos!!, esto es  mi contador ${viewModel.contador}"
        }
    }

    fun initLifecycle(){
        lifecycle.addObserver(LifeCycleObserverClass(this))
    }

    fun initViewModel(){
        viewModel = ViewModelProviders.of(this).get(ViewModelClass::class.java)
        // Make Observer:
        val nameObserver = Observer<String> {
            binding.message2TextView.text = it.toString()
        }
        // assig observer:
        viewModel.getCurrentName().observe(this, nameObserver)
    }
}
```

📄 ViewModel:

```
class ViewModelClass: ViewModel() {

    var contador: Int = 0 // property ViewModel
    private var mCurrentName: MutableLiveData<String> = MutableLiveData() // @Published  // <<===

    fun getCurrentName(): MutableLiveData<String> {
        return mCurrentName
    }
}
```
