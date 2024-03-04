using AndroidX.Fragment.App;

namespace RTSPPlayerApp.Droid
{
    public class SomethingImpl : ISomething
    {
        private FragmentManager _manager;

        public SomethingImpl(FragmentManager manager)
        {
            _manager = manager;
        }

        public void DoIt()
        {
            Com.Ocuvera.Rtsplibrary.MyDialogBox b = new Com.Ocuvera.Rtsplibrary.MyDialogBox();
            b.Show(_manager, null);
        }
    }
}