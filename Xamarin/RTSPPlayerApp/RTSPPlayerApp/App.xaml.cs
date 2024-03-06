using RTSPPlayerApp.Services;
using Xamarin.Forms;

namespace RTSPPlayerApp
{
    public partial class App : Application
    {

        public static ISomething Something { get; private set; }

        public App(ISomething something)
        {
            InitializeComponent();

            DependencyService.Register<MockDataStore>();
            Something = something;
            MainPage = new AppShell();
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
