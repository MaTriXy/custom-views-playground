package il.co.galex.playground.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import il.co.galex.playground.view.R;

public class MainNoXmlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setLayoutParams(params);

        TextView textView = new TextView(this);

        LinearLayout.LayoutParams textViewParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
        textViewParams.setMargins(10, 10, 0, 0);
        textView.setLayoutParams(textViewParams);
        textView.setText(R.string.hello_attenders);
        linearLayout.addView(textView);

        setContentView(linearLayout);
    }
}
