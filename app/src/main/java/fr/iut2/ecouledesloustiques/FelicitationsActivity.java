package fr.iut2.ecouledesloustiques;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FelicitationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_felicitations);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Récupérez les extras de l'intent
        Class<?> nextActivityCour = (Class<?>) getIntent().getSerializableExtra("nextActivityCour");
        Class<?> nextActivityMultiplication = (Class<?>) getIntent().getSerializableExtra("nextActivityMultiplication");

        // Configurez les onClickListeners pour vos boutons
        Button courButton = findViewById(R.id.courButton);
        Button multiplicationButton = findViewById(R.id.multiplicationButton);

        courButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FelicitationsActivity.this, nextActivityCour);
                startActivity(intent);
            }
        });

        multiplicationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(FelicitationsActivity.this, nextActivityMultiplication);
                startActivity(intent);
            }
        });
    }
}
