
import android.hardware.Camera;
import android.app.Activity;
import android.content.Context;

public class MainActivity extends Activity  implements SensorEventListener {
    private Camera mCamera;
    private CameraView cameraView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    protected void onResume(){
        super.onResume();
        mCamera = getCameraInstance();
        mCamera.setDisplayOrientation(90);
        cameraView = new CameraView(this, mCamera);
        mainView.addView(cameraView);
        mainView.bringChildToFront(buttonView);
        senSensorManager.registerListener(this, senRotation, SensorManager.SENSOR_DELAY_GAME);
    }

    /** A safe way to get an instance of the Camera object. */
    public Camera getCameraInstance(){
        Camera c = null;
        try{
            c = Camera.open(); // attempt to get a Camera instance
            Camera.Parameters parameters = c.getParameters();
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            c.setParameters(parameters);
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }

        return c; // returns null if camera is unavailable
    }
}
