package com.bullsheep.bullsheepfood_android.ui.stats;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bullsheep.bullsheepfood_android.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StatsAdapter extends RecyclerView.Adapter<StatsAdapter.StatsViewHolder> implements OnChartValueSelectedListener {
    // TODO: 30.04.19 Replace with real data model
    private List<String> statistics;

    private PercentFormatter valueFormatter = new PercentFormatter();

    StatsAdapter(List<String> statistics) {
        this.statistics = statistics;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.stats_item, parent, false);
        return new StatsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        String currentStat = statistics.get(position);
        holder.dayOfWeekTextView.setText(currentStat);

        setChartData(holder.chart);
    }

    @Override
    public long getItemId(int position) {
        // TODO: 30.04.19 Replace after we get objects with ids.
        return statistics.get(position).hashCode();
    }

    private void setChartData(PieChart chart) {
        ArrayList<PieEntry> stats = new ArrayList<>();

        stats.add(new PieEntry(945f, "Carbs"));
        stats.add(new PieEntry(1040f, "Proteins"));
        stats.add(new PieEntry(1133f, "Fats"));
        PieDataSet dataSet = new PieDataSet(stats, null);
        dataSet.setSliceSpace(3f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(valueFormatter);
        data.setValueTextSize(14);
        data.setValueTextColor(Color.WHITE);

        chart.setUsePercentValues(true);
        chart.setData(data);
        chart.setDescription(null);
        chart.getLegend().setEnabled(false);
        chart.setNoDataText("No data found");
        chart.setCenterText(generateCenterSpannableText());
        chart.animateXY(1000, 1000);
    }

    private SpannableString generateCenterSpannableText() {
        // TODO: 30.04.19  replace with actual value of kcal
        SpannableString styledString = new SpannableString("2222 kcal");
        styledString.setSpan(new RelativeSizeSpan(1.7f), 0, styledString.length(), 0);
        styledString.setSpan(new StyleSpan(Typeface.ITALIC), 0, styledString.length(), 0);
        return styledString;
    }

    @Override
    public int getItemCount() {
        return statistics.size();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    /**
     * @see <a href="https://stackoverflow.com/a/33702348/7805359">Marked static for omit
     * direct reference to adapter</a>
     */
    static class StatsViewHolder extends RecyclerView.ViewHolder {
        private PieChart chart;
        private TextView dayOfWeekTextView;

        StatsViewHolder(@NonNull View itemView) {
            super(itemView);

            chart = itemView.findViewById(R.id.stats__chart);
            dayOfWeekTextView = itemView.findViewById(R.id.stats__day_of_week);
        }
    }
}

