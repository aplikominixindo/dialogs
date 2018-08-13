package id.yusufrizalh.androiddialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnAlert, btnConfirmMultiple, btnConfirmSingle, btnListView,
            btnEditText, btnWebView, btnDatePicker, btnTimePicker;

    // ambil library kalender
    Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inisialisasi komponen
        btnAlert = (Button) findViewById(R.id.btnAlertDialog);
        btnConfirmMultiple = (Button) findViewById(R.id.btnConfirmMultipleDialog);
        btnConfirmSingle = (Button) findViewById(R.id.btnConfirmSingleDialog);
        btnListView = (Button) findViewById(R.id.btnListViewDialog);
        btnEditText = (Button) findViewById(R.id.btnEditTextDialog);
        btnWebView = (Button) findViewById(R.id.btnWebViewDialog);
        btnDatePicker = (Button) findViewById(R.id.btnDatePickerDialog);
        btnTimePicker = (Button) findViewById(R.id.btnTimePickerDialog);

        // event handling alert dialog
        btnAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bukaAlertDialog();
            }
        });

        // event handling confirm dialog - multiple
        btnConfirmMultiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bukaConfirmDialogMultiple();
            }
        });

        // event handling confirm dialog - single
        btnConfirmSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bukaConfirmDialogSingle();
            }
        });

        // event handling list view dialog
        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bukaListViewDialog();
            }
        });

        // event handling edit text dialog
        btnEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bukaEditTextDialog();
            }
        });

        // event handling datepicker dialog
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bukaDatepickerDialog();
            }
        });

        // event handling time picker dialog
        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bukaTimePickerDialog();
            }
        });

    }

    private void bukaTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker,
                                  int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);

                String waktu = DateFormat.getTimeInstance(
                        DateFormat.MEDIUM       // LONG
                ).format(myCalendar.getTime());

                Toast.makeText(getApplicationContext(),
                        "Waktu : " + waktu, Toast.LENGTH_SHORT).show();
            }
        }, myCalendar.get(Calendar.HOUR_OF_DAY),
            myCalendar.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    private void bukaDatepickerDialog() {
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker datePicker, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                myCalendar.set(Calendar.YEAR, year);
                                myCalendar.set(Calendar.MONTH, monthOfYear);
                                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                String tanggal = DateFormat.getDateInstance(
                                        DateFormat.MEDIUM
                                ).format(myCalendar.getTime());

                                Toast.makeText(getApplicationContext(),
                                        "Tanggal: " + tanggal, Toast.LENGTH_SHORT).show();
                            }
                        }, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void bukaEditTextDialog() {
        // siapkan layout dahulu
        LinearLayout layout = new LinearLayout(MainActivity.this);
        layout.setOrientation(LinearLayout.VERTICAL);

        // buat edit text
        final EditText edit1 = new EditText(MainActivity.this);
            edit1.setHint("Judul lagu");
            edit1.setPadding(6, 6, 6, 6);
            layout.addView(edit1);
        final EditText edit2 = new EditText(MainActivity.this);
            edit2.setHint("Penyanyi lagu");
            edit2.setPadding(6, 6, 6, 6);
            layout.addView(edit2);

        final AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Tulis Judul")
                .setMessage("Tuliskan sebuah judul lagu dan penyanyinya!")
                .setView(layout)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "Judul: " + edit1.getText() + "\n" +
                                        "Penyanyi: " + edit2.getText(),
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .setCancelable(false).create();
        dialog.show();
    }

    private void bukaListViewDialog() {
        final String[] nama_nama = getResources().getStringArray(
                R.array.list_view_dialog
        );
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Daftar Nama")
                .setItems(nama_nama, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "Nama: " + nama_nama[i],
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .setCancelable(false).show();
    }

    private void bukaConfirmDialogSingle() {
        String[] singleItems = getResources().getStringArray(
                R.array.confirm_dialog_single
        );
        int item = 0;
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Pilih Gender : ")
                .setSingleChoiceItems(singleItems, item,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface,
                                                int i) {
                                Toast.makeText(getApplicationContext(),
                                        "Index gender: " + i,
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                .setPositiveButton("OK", null)
                .setNegativeButton("Cancel", null)
                .setCancelable(false).show();
    }

    private void bukaConfirmDialogMultiple() {
        String[] multipleItems = getResources().getStringArray(
                R.array.confirm_dialog_multiple
        );
        final boolean[] checkedItem = {
                false, false, false, false, false };

        // convert array menjadi list
        final List<String> multipleLists = Arrays.asList(multipleItems);

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Favorite Film: ")
                .setMultiChoiceItems(multipleItems, checkedItem,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface,
                                                int index, boolean isChecked) {
                                // ubah status item yg di-check
                                checkedItem[index] = isChecked;

                                // ambil item yg statusnya isChecked
                                String currentItem = multipleLists.get(index);

                                Toast.makeText(getApplicationContext(),
                                        "Index film: " + index + "\n" +
                                        "Judul film: " + currentItem,
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
        .setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int index) {
                        for (int i = 0; i < checkedItem.length; i++) {
                            boolean checked = checkedItem[i];
                            if (checked) {
                                /*
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Daftar Film Favorit Pilihan")
                                        .setMessage(multipleLists.get(i) + "\n")
                                        .setCancelable(true)
                                        .show();
                                */
                                Toast.makeText(getApplicationContext(),
                                        "Film Favorit Pilihan : " + multipleLists.get(i),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                })
        .setNegativeButton("Cancel", null)
        .setCancelable(false).show();
    }

    private void bukaAlertDialog() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Alert Dialog")
                .setMessage("Ini adalah alert dialog android.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "Klik tombol OK", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "Klik tombol Cancel", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton("Neutral", null)
                .setCancelable(true)
                .show();
    }
}
