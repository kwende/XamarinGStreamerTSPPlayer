using Android.App;
using Android.Content.PM;
using Android.OS;
using Android.Runtime;
using Java.Lang;

namespace RTSPPlayerApp.Droid
{
    [Activity(Label = "RTSPPlayerApp", Icon = "@mipmap/icon", Theme = "@style/MainTheme", MainLauncher = true, ConfigurationChanges = ConfigChanges.ScreenSize | ConfigChanges.Orientation | ConfigChanges.UiMode | ConfigChanges.ScreenLayout | ConfigChanges.SmallestScreenSize)]
    public class MainActivity : global::Xamarin.Forms.Platform.Android.FormsAppCompatActivity
    {
        protected override void OnCreate(Bundle savedInstanceState)
        {
            //Com.Ocuvera.Rtsplibrary.Poop p = new Com.Ocuvera.Rtsplibrary.Poop();
            //Com.Ocuvera.Rtsplibrary.MyDialogBox b = new Com.Ocuvera.Rtsplibrary.MyDialogBox();
            //b.Show(this.SupportFragmentManager, null);
            //int ret = p.DoPoop();

            base.OnCreate(savedInstanceState);

            Xamarin.Essentials.Platform.Init(this, savedInstanceState);
            global::Xamarin.Forms.Forms.Init(this, savedInstanceState);

            JavaSystem.LoadLibrary("gstreamer_android");
            JavaSystem.LoadLibrary("native");

            //

            LoadApplication(new App(new SomethingImpl(this.SupportFragmentManager, Android.App.Application.Context)));
        }
        public override void OnRequestPermissionsResult(int requestCode, string[] permissions, [GeneratedEnum] Android.Content.PM.Permission[] grantResults)
        {
            Xamarin.Essentials.Platform.OnRequestPermissionsResult(requestCode, permissions, grantResults);

            base.OnRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}