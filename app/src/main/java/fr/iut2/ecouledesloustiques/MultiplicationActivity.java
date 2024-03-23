package fr.iut2.ecouledesloustiques;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class MultiplicationActivity extends Activity {
    private NumberPicker numberPicker;
    private Button validateButton;
    private int tableNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        numberPicker = findViewById(R.id.numberPicker);
        validateButton = findViewById(R.id.validateButton);

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableNumber = numberPicker.getValue();
                goToQuestionActivity();
            }
        });
    }

    private void goToQuestionActivity() {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("tableNumber", tableNumber);
        startActivity(intent);
    }
}
