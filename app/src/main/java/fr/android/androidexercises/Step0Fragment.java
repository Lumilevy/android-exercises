package fr.android.androidexercises;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Step0Fragment extends Fragment {

    private static final String step0 = "This is step 0";

    private TextView textView;
    private OnNextStep0Listener listener;
    private Button nextButton;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.listener = (OnNextStep0Listener) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step0, container, false);
        // TODO findViewById textView (TextView)
        this.textView = view.findViewById(R.id.textView);
        // TODO findViewById nextButton (Button)
        this.nextButton = view.findViewById(R.id.nextButton);

        textView.setText(step0);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO call onNext() from listener
                listener.onNext();

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // TODO setText(step0)
    }

    public interface OnNextStep0Listener {
        public void onNext();
    }
}
