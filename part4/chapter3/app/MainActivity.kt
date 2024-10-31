class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Chapter3Theme {
                // Scaffold, modifier = 화면을 구성하는 큰 들 ( 부모 레이아웃 설정 )
                // 현재는 화면을 꽉 채울거냐, 에 따른 설정
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TextExample(
                        modifier = Modifier.padding(innerPadding),
                        name = "Android",
                    )
                    ButtonExample(
                        modifier = Modifier.padding(innerPadding),
                        onButtonClicked =  {
                        Toast.makeText(this, "Send Clicked", Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }
    }
}