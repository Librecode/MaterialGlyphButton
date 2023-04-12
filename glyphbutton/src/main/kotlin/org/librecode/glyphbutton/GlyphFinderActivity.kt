package org.librecode.glyphbutton

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.librecode.glyphbutton.constant.Glyph
import org.librecode.glyphbutton.databinding.ActivityGlyphFinderBinding
import org.librecode.glyphbutton.widget.MaterialGlyphButton
import java.util.*

class GlyphFinderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGlyphFinderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlyphFinderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycler()
        title = "Available Icons - ${binding.glyphRecycler.adapter?.itemCount ?: 0}"

        binding.dialogLayout.detailsCloseButton.setOnClickListener {
            binding.dialogLayout.glyphHelperLayout.visibility = View.GONE
        }
    }

    private fun initRecycler() {
        binding.glyphRecycler.layoutManager = GridLayoutManager(this, 6)
        binding.glyphRecycler.adapter = GlyphAdapter()
    }

    @SuppressLint("SetTextI18n")
    fun glyphClicked(glyph: Glyph) {
        binding.dialogLayout.glyphHelperLayout.visibility = View.VISIBLE
        binding.dialogLayout.helperGlyphIcon.setGlyph(glyph)
        binding.dialogLayout.iconDetailsTitle.text = "Icon - " +
            glyph.toString().lowercase().split('_').filter {
                it != "n"
            }.joinToString(" ")
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }

        binding.dialogLayout.textUsageCode.text =
            "val button: MaterialGlyphButton\nbutton.setGlyph(Glyph.$glyph)"
        binding.dialogLayout.textUsageXml.text = "<org.librecode.glyphbutton.widget" +
                ".MaterialGlyphButton\n\t\tapp:material_icon=\"${
                    glyph.toString().lowercase(Locale.ROOT)
                }\" />"
    }

    private inner class GlyphAdapter : RecyclerView.Adapter<GlyphHolder>() {
        val glyphs = Glyph.values()

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): GlyphHolder =
            GlyphHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.glyph_list_item, viewGroup, false)
            )


        override fun getItemCount(): Int {
            return glyphs.size
        }

        override fun onBindViewHolder(viewHolder: GlyphHolder, position: Int) {
            viewHolder.glyphIcon.setGlyph(glyphs[position])
            viewHolder.glyphIcon.setOnClickListener {
                glyphClicked(glyphs[position])
            }
        }
    }

    private class GlyphHolder(view: View) : RecyclerView.ViewHolder(view) {
        val glyphIcon: MaterialGlyphButton = view.findViewById(R.id.glyph_icon)
    }
}
