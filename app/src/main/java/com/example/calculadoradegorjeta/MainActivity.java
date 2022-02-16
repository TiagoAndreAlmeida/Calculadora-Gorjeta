package com.example.calculadoradegorjeta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Double gorjetaValor, valor = 0.0;
    private TextInputEditText editValor, editGorjeta, editTotal;
    private SeekBar seekBar;
    private TextView textPct, textTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editGorjeta = findViewById(R.id.editGorjeta);
        editValor = findViewById(R.id.editValor);
        seekBar = findViewById(R.id.seekPct);
        textPct = findViewById(R.id.textPct);
        textTotal = findViewById(R.id.totalText);

        editValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    valor = Double.parseDouble(s.toString());
                    if (valor > 0) {
                        seekBar.setEnabled(true);
                    } else {
                        disableSeek();
                    }
                    calcGorjeta();
                } else {
                    valor = 0.0;
                    disableSeek();
                }

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        editGorjeta.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0) {
                    if(Double.parseDouble(s.toString()) <= valor) {
                        gorjetaValor = Double.parseDouble(s.toString());
                        if (valor > 0) {
                            seekBar.setEnabled(true);
                        } else {
                            disableSeek();
                        }
                        calcPct();
                    } else {
                        editGorjeta.setText(gorjetaValor+"");
                    }

                } else {
                    seekBar.setProgress(0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        disableSeek();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textPct.setText(progress+"%");
                if(progress > 0) {
                    calcGorjeta();
                } else {
                    editGorjeta.setText("");
                }
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
        if(gorjetaValor > 0) {
            Double pctValor = (100 * gorjetaValor) / 100;
            Integer progress = (int) Math.round(pctValor);
            seekBar.setProgress(progress);
            calcTotal();
        }
    }

    private void calcGorjeta() {
        if(valor > 0) {
            Double gorjeta = (valor * seekBar.getProgress()) / 100;
            editGorjeta.setText(gorjeta+"");
            calcTotal();
        }
    }

    private void disableSeek() {
        seekBar.setProgress(0);
        seekBar.setEnabled(false);
    }

    private void calcTotal() {
        Double _total = valor + gorjetaValor;
        textTotal.setText("R$ "+_total);
    }
}