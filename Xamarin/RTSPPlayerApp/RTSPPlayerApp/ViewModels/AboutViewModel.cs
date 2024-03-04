using System.Windows.Input;
using Xamarin.Forms;

namespace RTSPPlayerApp.ViewModels
{
    public class AboutViewModel : BaseViewModel
    {
        public AboutViewModel()
        {
            Title = "About";
            OpenWebCommand = new Command(async () =>
            {
                App.Something.DoIt();
            });
        }

        public ICommand OpenWebCommand { get; }
    }
}