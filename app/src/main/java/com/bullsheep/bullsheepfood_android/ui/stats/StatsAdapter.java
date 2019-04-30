package com.bullsheep.bullsheepfood_android.ui.stats;

import android.graphics.Color;
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

    private void setChartData(PieChart chart) {
        ArrayList<PieEntry> stats = new ArrayList<>();

        stats.add(new PieEntry(945f, "Carbs"));
        stats.add(new PieEntry(1040f, "Proteins"));
        stats.add(new PieEntry(1133f, "Fats"));
        PieDataSet dataSet = new PieDataSet(stats, null);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(valueFormatter);
        data.setValueTextSize(14);
        data.setValueTextColor(Color.WHITE);
        chart.setUsePercentValues(true);
        chart.setData(data);
        chart.setDescription(null);
        chart.setDrawEntryLabels(false);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.animateXY(1000, 1000);
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

