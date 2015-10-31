package builditbigger.gradle.udacity.com.jokeandroidlib;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class AndLibFragment extends Fragment {

    public AndLibFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.and_lib_fragment_main, container, false);

        ( (TextView)root.findViewById(R.id.tv1) ).setText(getActivity().getIntent().getStringExtra("joke"));

        return root;
    }

}
