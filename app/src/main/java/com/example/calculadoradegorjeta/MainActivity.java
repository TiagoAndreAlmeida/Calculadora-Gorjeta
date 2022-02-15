package com.example.calculadoradegorjeta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Double gorjetaValue = 0.0;
    private TextInputEditText editValor, editGorjeta, editTotal;
    private SeekBar seekBar;
    private TextView textPct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editGorjeta = findViewById(R.id.editGorjeta);
        editTotal = findViewById(R.id.editTotal);
        editValor = findViewById(R.id.editValor);
        seekBar = findViewById(R.id.seekPct);
        textPct = findViewById(R.id.textPct);

        editGorjeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                calcGorjeta();
            }

            @Override
            public void afterTextChanged(Editable s) {
                textPct.setText(s.toString());
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textPct.setText(progress+"%");
                calcPct();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void calcPct() {
        Double _editValue = Double.parseDouble(editValor.getText().toString());
        Double valorGorjeta = (_editValue * seekBar.getProgress()) / 100;
        editGorjeta.setText(valorGorjeta+"");
    }

    private void calcGorjeta() {
        // 1 - verificar se o valor final e mair que zero
        // 2 - validar se o valor digitado ne é maior que o valor final
        // 3 - ajustar o progress do seekbar de acordo com o calculo
    }
}