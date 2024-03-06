using Android.Content;
using AndroidX.Fragment.App;

namespace RTSPPlayerApp.Droid
{
    public class SomethingImpl : ISomething
    {
        private FragmentManager _manager;
        private Context _context;

        public SomethingImpl(FragmentManager manager, Context context)
        {
            _manager = manager;
            _context = context;
        }

        public string DoIt()
        {
            //Org.Freedesktop.Gstreamer.GStreamer.Init(_context);

            //Com.Ocuvera.Rtsplibrary.Poop p = new Com.Ocuvera.Rtsplibrary.Poop();
            //string version = p.GStreamerInfo;

            //p.NativeClassInit();
            //p.NativeInit();

            //bool isInitialized = (bool)p.IsGStreamerInitialized;
            //return version;
            return "";

        }
    }
}