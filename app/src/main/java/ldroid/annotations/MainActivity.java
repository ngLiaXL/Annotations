package ldroid.annotations;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static ldroid.annotations.MainManager.RUN_ERROR;
import static ldroid.annotations.MainManager.RUN_SUCCESS;

public class MainActivity extends AppCompatActivity implements MainManager.Callback {


    @Colors.LightColors
    private int color;


    TextView mTv;

    MainManager mMainMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = (TextView) findViewById(R.id.textview);
        mMainMgr = new MainManager();
        mMainMgr.register(this);

        findViewById(R.id.success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainMgr.run(RUN_SUCCESS);
            }
        });
        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMainMgr.run(RUN_ERROR);
            }
        });
    }

    @MainManager.CallbackAnnotation(RUN_SUCCESS)
    @Override
    public void onSuccess() {
        mTv.setText("onSuccess");
    }

    @MainManager.CallbackAnnotation(RUN_ERROR)
    @Override
    public void onError(String errorCode) {
        mTv.setText(errorCode);
    }


}
