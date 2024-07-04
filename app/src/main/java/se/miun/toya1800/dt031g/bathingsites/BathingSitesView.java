package se.miun.toya1800.dt031g.bathingsites;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class BathingSitesView extends ConstraintLayout {

    public BathingSitesView(Context context) {
        super(context);
        init(context, null);
    }

    public BathingSitesView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BathingSitesView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray typedArray =
                context.getTheme().obtainStyledAttributes(attributeSet,
                        R.styleable.bathingSitesView, 0, 0);

        if(attributeSet != null) {
            inflate(context, R.layout.bathing_sites_view, this);

            try {
                // Set amount of sites
                setBathSites("0");
            } finally {
                typedArray.recycle();
            }
        }
    }

    public void setBathSites(String numOfSites) {
        TextView num_sites = findViewById(R.id.num_bath_sites);
        num_sites.setText(numOfSites);
    }
}
