/**
 * Cette classe représente l'activité permettant à l'utilisateur de choisir une table de multiplication.
 * L'utilisateur peut sélectionner une table de multiplication à l'aide d'un NumberPicker et valider sa sélection en appuyant sur un bouton.
 * Une fois la sélection validée, l'utilisateur est redirigé vers l'activité QuestionActivity pour répondre aux questions de multiplication.
 */
package fr.iut2.ecouledesloustiques;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MultiplicationActivity extends Activity {
    // Éléments de l'interface utilisateur
    private NumberPicker numberPicker;
    private Button validateButton;
    private Switch timerSwitch;

    // Numéro de la table de multiplication sélectionnée
    private int tableNumber;

    // CountDownTimer
    private CountDownTimer countDownTimer;

    // Déclaration des boutons radio
    private RadioButton additionRadioButton;
    private RadioButton subtractionRadioButton;
    private RadioButton multiplicationRadioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);

        // Initialisation des éléments de l'interface utilisateur
        numberPicker = findViewById(R.id.numberPicker);
        validateButton = findViewById(R.id.validateButton);
        timerSwitch = findViewById(R.id.switch1);

        // Définition des valeurs min et max du NumberPicker
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);

        // Définition du comportement du bouton de validation
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération de la valeur sélectionnée dans le NumberPicker
                tableNumber = numberPicker.getValue();
                // Lancement de l'activité QuestionActivity avec le numéro de table en extra
                goToQuestionActivity();
            }
        });

    }

    /**
     * Méthode permettant de lancer l'activité QuestionActivity avec le numéro de table de multiplication en extra.
     */
    private void goToQuestionActivity() {
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("tableNumber", tableNumber);
        intent.putExtra("isTimerEnabled", timerSwitch.isChecked()); // Passer la valeur du switch (true si activé, false sinon)
        // Ajouter l'état du bouton radio sélectionné
        RadioGroup selectBtn = findViewById(R.id.radioGroup);
        RadioButton selectRadiobtn = findViewById(selectBtn.getCheckedRadioButtonId());
        intent.putExtra("selectedRadioButtonId", selectRadiobtn.getText());
        startActivity(intent);
    }

}
