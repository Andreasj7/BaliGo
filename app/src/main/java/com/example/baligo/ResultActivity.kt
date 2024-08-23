import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.baligo.R
import com.example.baligo.ml.BaliRecommendationModel

class ResultActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultTextView = findViewById(R.id.resultTextView)

        // Retrieve data from the intent
        val recommendation = intent.getParcelableExtra<Recommendation>("recommendation")

        // Display data if available
        if (recommendation != null) {
            displayRecommendation(recommendation)
        } else {
            resultTextView.text = "No recommendation data available"
        }
    }

    private fun displayRecommendation(recommendation: Recommendation) {
        // Display the recommendation data as needed
        val resultText = buildString {
            append("Nama Tempat: ${recommendation.name}\n")
            append("Link: ${recommendation.link}\n")
            append("Kategori: ${recommendation.category}\n")
            append("Foto URL: ${recommendation.photoUrl}\n")
            append("Review: ${recommendation.review}\n")
            append("Rating: ${recommendation.rating}\n")
        }
        resultTextView.text = resultText
    }
}
