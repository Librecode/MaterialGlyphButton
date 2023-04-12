package org.librecode.glyphbutton.widget

import android.content.Context
import androidx.appcompat.widget.AppCompatTextView
import android.util.AttributeSet
import org.librecode.glyphbutton.constant.Glyph
import org.librecode.glyphbutton.R
import org.librecode.glyphbutton.util.GlyphFont

class MaterialGlyphButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                                    defStyleAttr: Int = 0) :
        AppCompatTextView(context, attrs, defStyleAttr) {
    private var enumCode: Int = -1

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.MaterialGlyphButton)
        enumCode = attributes.getInt(R.styleable.MaterialGlyphButton_material_icon, -1)
        attributes.recycle()

        if (enumCode > -1) {
            setGlyph(glyph = Glyph.getGlyph(enumCode))
        }

        typeface = GlyphFont.getTypeface(getContext())
    }

    fun setGlyph(glyph: Glyph?) {
        text = if (glyph != null) Glyph.getIcon(glyph) else ""
    }
}