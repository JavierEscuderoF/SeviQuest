package encuesta;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.seviquest.R;

public class VistaEncuesta extends ActionBarActivity {

	List<Encuesta> encuestas;
	int coloreados[] = new int[] {0,0,0,0};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_encuestas);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		XmlResourceParser parser = getResources().getXml(R.xml.encuestas);
		encuestas = new ArrayList<Encuesta>();
		encuestas = obtenerEncuestas(parser);

		ListView listaEncuestas = (ListView) findViewById(R.id.ListaEncuestas);

		AdaptadorEncuestas adaptador = new AdaptadorEncuestas(
				VistaEncuesta.this);
		listaEncuestas.setAdapter(adaptador);

		listaEncuestas.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> pariente, final View view,
					final int posicion, long id) {
				Encuesta elegido = (Encuesta) pariente
						.getItemAtPosition(posicion);

				final AlertDialog.Builder alert = new AlertDialog.Builder(
						VistaEncuesta.this);

				alert.setMessage(elegido.getCuerpo());
				alert.setTitle(elegido.getTitulo());
				alert.setNegativeButton(elegido.getOpcionB(),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();

								if (coloreados[posicion] == 1) {
									Toast toast = Toast.makeText(getApplicationContext(),
											"Ya ha respondido esta encuesta",
											Toast.LENGTH_SHORT);
									toast.show();
								} else {
									coloreados[posicion] = 1;
									int color = R.color.amarillo;
									view.setBackgroundColor(getResources()
											.getColor(color));
									Toast toast = Toast.makeText(getApplicationContext(),
											"Muchas gracias por participar",
											Toast.LENGTH_SHORT);
									toast.show();
								}

							}
						});
				alert.setPositiveButton(elegido.getOpcionA(),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								if (coloreados[posicion] == 1) {
									Toast toast = Toast.makeText(getApplicationContext(),
											"Ya ha respondido esta encuesta",
											Toast.LENGTH_SHORT);
									toast.show();
								} else {
									coloreados[posicion] = 1;
									int color = R.color.amarillo;
									view.setBackgroundColor(getResources()
											.getColor(color));
									Toast toast = Toast.makeText(getApplicationContext(),
											"Muchas gracias por participar",
											Toast.LENGTH_SHORT);
									toast.show();
								}
							}
						});
				alert.show();

			}
		});

	}

	class AdaptadorEncuestas extends ArrayAdapter<Encuesta> {
		Activity context;

		AdaptadorEncuestas(Activity context) {
			super(context, R.layout.encuesta_individual, encuestas);
			this.context = context;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = context.getLayoutInflater();
			View item = inflater.inflate(R.layout.encuesta_individual, null);

			TextView lblIcon = (TextView) item.findViewById(R.id.encuestaicon);
			lblIcon.setText("#" + (position + 1));

			TextView lblTitulo = (TextView) item
					.findViewById(R.id.EncuestaTitulo);
			lblTitulo.setText(encuestas.get(position).getTitulo());

			TextView lblFecha = (TextView) item
					.findViewById(R.id.EncuestaFecha);
			lblFecha.setText(encuestas.get(position).getFecha());

			return (item);
		}
	}

	private List<Encuesta> obtenerEncuestas(XmlResourceParser parse) {
		List<Encuesta> encuestas = null;
		XmlResourceParser parser = parse;

		int evento;
		try {
			evento = parser.getEventType();

			Encuesta encuestaActual = null;
			while (evento != XmlResourceParser.END_DOCUMENT) {
				String etiqueta = null;
				switch (evento) {
				case XmlResourceParser.START_DOCUMENT:
					encuestas = new ArrayList<Encuesta>();
					break;
				case XmlResourceParser.START_TAG:
					etiqueta = parser.getName();
					if (etiqueta.equals("item")) {
						encuestaActual = new Encuesta();
					} else if (encuestaActual != null) {
						if (etiqueta.equals("titulo")) {
							encuestaActual.setTitulo(parser.nextText());
						} else if (etiqueta.equals("cuerpo")) {
							encuestaActual.setCuerpo(parser.nextText());
						} else if (etiqueta.equals("fecha")) {
							encuestaActual.setFecha(parser.nextText());
						} else if (etiqueta.equals("imagen")) {
							encuestaActual.setImagen(parser.nextText());
						} else if (etiqueta.equals("opcionA")) {
							encuestaActual.setOpcionA(parser.nextText());
						} else if (etiqueta.equals("opcionB")) {
							encuestaActual.setOpcionB(parser.nextText());
						}
					}
					break;

				case XmlResourceParser.END_TAG:
					etiqueta = parser.getName();
					if (etiqueta.equals("item") && encuestaActual != null) {
						encuestas.add(encuestaActual);
					}
					break;
				}
				evento = parser.next();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return encuestas;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_encuestas, menu);
		return super.onCreateOptionsMenu(menu);
	}
}