package notepad.xdream.cf.notepad;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import notepad.xdream.cf.notepad.tools.BackHandledFragment;
import notepad.xdream.cf.notepad.tools.BackHandledInterface;

public class MainActivity extends AppCompatActivity implements BackHandledInterface{

    private BackHandledFragment mBackHandedFragment;
    private boolean hadIntercept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState==null){
             getSupportFragmentManager().beginTransaction()
                     .add(R.id.container,new BlankFragment())
                     .commit();}
    }


    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.mBackHandedFragment = selectedFragment;
    }
    @Override
    public void onBackPressed() {
        if(mBackHandedFragment == null || !mBackHandedFragment.onBackPressed()){
            if(getSupportFragmentManager().getBackStackEntryCount() == 0){
                super.onBackPressed();
            }else{
                getSupportFragmentManager().popBackStack();
            }
        }
    }
}

