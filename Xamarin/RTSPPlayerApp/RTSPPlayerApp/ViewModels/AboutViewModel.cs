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
                string ret = App.Something.DoIt();
                Text = ret;
            });
        }

        private string _text;
        public string Text
        {
            get => _text;
            set => SetProperty(ref _text, value);
        }

        public ICommand OpenWebCommand { get; }
    }
}