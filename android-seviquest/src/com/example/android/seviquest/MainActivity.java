package com.example.android.seviquest;

import com.example.android.seviquest.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import encuesta.VistaEncuesta;

public class MainActivity extends ActionBarActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getSupportActionBar().hide();
		
		final Button botonEncuesta = (Button) findViewById(R.id.BotonEncuestas);
		final Button botonTerminadas = (Button) findViewById(R.id.BotonTerminadas);
		final Button botonPropuesta = (Button) findViewById(R.id.BotonPropuestas);

		botonEncuesta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						VistaEncuesta.class);

				startActivity(intent);
			}
		});

		botonTerminadas.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						VistaEncuesta.class);

				startActivity(intent);
			}
		});

		botonPropuesta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						VistaPropuesta.class);

				startActivity(intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

}