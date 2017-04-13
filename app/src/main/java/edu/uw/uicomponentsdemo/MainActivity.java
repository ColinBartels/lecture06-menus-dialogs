package edu.uw.uicomponentsdemo;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MoviesFragment frag = MoviesFragment.newInstance("Menu");
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, frag)
                .commit();
    }

    public void handleButton(View v) {
        Log.v(TAG, "You clicked me!");

        ActionBar actionBar = getSupportActionBar();

        if (actionBar.isShowing()) {
            actionBar.hide();
        } else {
            actionBar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.hi_menu_item:
                Log.v(TAG, "Hi!!");
                showHelloDialog();
                return true;
            case R.id.bye_menu_item:
                Log.v(TAG, "Byebye!!");
                Context context = this;
                String message = "Bye bye!! Have a good weekend";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
                return true;
            case R.id.third_menu_item:
                Log.v(TAG, "??????????");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showHelloDialog() {
        Log.v(TAG, "Hi!!!");

        HelloDialogFragment frag = HelloDialogFragment.newInstance();
        frag.show(getSupportFragmentManager(), null);
    }

    public static class HelloDialogFragment extends DialogFragment {

        public static HelloDialogFragment newInstance() {
            Bundle args = new Bundle();
            HelloDialogFragment fragment = new HelloDialogFragment();
            fragment.setArguments(args);
            return fragment;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Hello")
                    .setMessage("This is me saying hello. Hello.");

            builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.v(TAG, "They said hi back");
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialogInterface) {
                    Log.v(TAG, "Why you not click my button?");
                }
            });

            AlertDialog dialog = builder.create();
            return dialog;
        }
    }
}
