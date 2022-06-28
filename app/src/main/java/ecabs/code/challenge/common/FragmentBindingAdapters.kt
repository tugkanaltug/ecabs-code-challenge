package ecabs.code.challenge.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*
import javax.inject.Inject

/**
 * Binding adapters that work with a fragment instance.
 */
class FragmentBindingAdapters @Inject constructor(val fragment: Fragment) {

    @BindingAdapter(value = ["imageUrl", "imageRequestListener"], requireAll = false)
    fun bindImage(imageView: ImageView, url: String?, listener: RequestListener<Drawable?>?) {
        Glide.with(fragment).load(url).listener(listener).into(imageView)
    }

    @BindingAdapter("bindServerDate")
    fun bindServerDate(textView: TextView, date: String?) {
        val simpleDateFormat = SimpleDateFormat("E, dd MMM yy HH:mm")
        textView.text = simpleDateFormat.format(Date.from(Instant.parse(date)))
    }
}