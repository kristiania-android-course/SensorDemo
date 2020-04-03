package no.kristiania.android.accelarometergyroscope

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity(), SensorEventListener {


    private var gyroScope: Sensor? = null
    lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val list = sensorManager.getSensorList(Sensor.TYPE_ALL)

        list.map {
            println(it.name)
        }

        gyroScope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    }

    override fun onResume() {
        super.onResume()
        gyroScope?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }

    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d("MainActivity", "xvalue" + event.values[0])
        Log.d("MainActivity", "yvalue" + event.values[1])
        Log.d("MainActivity", "zvalue" + event.values[2])
    }


}
