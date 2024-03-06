using Android.Views;
using RTSPPlayerApp;
using RTSPPlayerApp.Droid;
using System.Runtime.Remoting.Contexts;
using Xamarin.Forms;
using Xamarin.Forms.Platform.Android;

[assembly: ExportRenderer(typeof(CameraPreview), typeof(AndroidPlayerRenderer))]
namespace RTSPPlayerApp.Droid
{
    public class AndroidPlayerRenderer : ViewRenderer<CameraPreview, Com.Ocuvera.Rtsplibrary.GStreamerSurfaceView>
    {
        private Com.Ocuvera.Rtsplibrary.GStreamerSurfaceView _surfaceView;
        private Context _context;

        private bool _initialized = false;

        public AndroidPlayerRenderer()
        {

        }

        protected override void OnElementChanged(ElementChangedEventArgs<CameraPreview> e)
        {
            base.OnElementChanged(e);

            if (!_initialized)
            {
                Org.Freedesktop.Gstreamer.GStreamer.Init(Android.App.Application.Context);

                Com.Ocuvera.Rtsplibrary.Poop p = new Com.Ocuvera.Rtsplibrary.Poop();
                string version = p.GStreamerInfo;

                p.NativeClassInit();
                p.NativeInit();

                bool isInitialized = (bool)p.IsGStreamerInitialized;
            }

            if (Control == null)
            {
                _surfaceView = new Com.Ocuvera.Rtsplibrary.GStreamerSurfaceView(Context);
                var surfaceView = (SurfaceView)_surfaceView;
                SetNativeControl(_surfaceView);
            }
        }
    }
}