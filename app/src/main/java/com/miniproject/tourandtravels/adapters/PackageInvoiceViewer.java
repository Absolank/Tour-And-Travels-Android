package com.miniproject.tourandtravels.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miniproject.tourandtravels.R;
import com.miniproject.tourandtravels.api.model.PackageInvoice;
import com.miniproject.tourandtravels.util.TimeConverter;

import java.util.Date;
import java.util.List;

public class PackageInvoiceViewer extends RecyclerView.Adapter<PackageInvoiceViewer.PackageInvoiceViewHolder> {
    private List<PackageInvoice> packageInvoices;

    public void setPackageInvoice(List<PackageInvoice> packageInvoice) {
        this.packageInvoices = packageInvoice;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PackageInvoiceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new PackageInvoiceViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.hotel_invoice, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PackageInvoiceViewHolder viewHolder, int i) {
        PackageInvoice invoice = packageInvoices.get(i);
        viewHolder.setData(invoice);
    }

    @Override
    public int getItemCount() {
        if(packageInvoices != null)
            return  packageInvoices.size();
        return 0;
    }

    class PackageInvoiceViewHolder extends RecyclerView.ViewHolder{
        TextView DepWeek, DepDay, DepMonth, CheckWeek, CheckDay, CheckMonth, NumDays, ID, Transaction, TotalPrice, BasePrice, Taxes, NumPersons;
        public PackageInvoiceViewHolder(@NonNull View itemView) {
            super(itemView);

            DepWeek = itemView.findViewById(R.id.dep_week);
            DepDay = itemView.findViewById(R.id.dep_day);
            DepMonth = itemView.findViewById(R.id.dep_month);
            TotalPrice = itemView.findViewById(R.id.total_price);
            CheckWeek = itemView.findViewById(R.id.check_out_week);
            CheckDay = itemView.findViewById(R.id.check_out_day);
            CheckMonth = itemView.findViewById(R.id.check_out_month);
            NumDays = itemView.findViewById(R.id.num_days);
            ID = itemView.findViewById(R.id.item_id);
            BasePrice = itemView.findViewById(R.id.base_price);
            Taxes = itemView.findViewById(R.id.taxes);
            Transaction = itemView.findViewById(R.id.transaction_id);
            NumPersons = itemView.findViewById(R.id.numPerson);

//            itemView.findViewById(R.id.textView23).setVisibility(View.GONE);
            itemView.findViewById(R.id.textView30).setVisibility(View.GONE);
            itemView.findViewById(R.id.textView17).setVisibility(View.GONE);
//            itemView.findViewById(R.id.textView20).setVisibility(View.GONE);
//            itemView.findViewById(R.id.textView18).setVisibility(View.GONE);
//            itemView.findViewById(R.id.base_price).setVisibility(View.GONE);
//            itemView.findViewById(R.id.taxes).setVisibility(View.GONE);
        }

        void setData(PackageInvoice invoice){
            try {
                String ci = invoice.getCheckInDate().substring(0, 10);
                String co = invoice.getCheckOutDate().substring(0, 10);
                Date cin = TimeConverter.dmy.parse(ci);
                Date cout = TimeConverter.dmy.parse(co);

                DepWeek.setText(TimeConverter.week.format(cin));
                DepDay.setText(TimeConverter.day.format(cin));
                DepMonth.setText(TimeConverter.month.format(cin));

                CheckMonth.setText(TimeConverter.month.format(cout));
                CheckDay.setText(TimeConverter.day.format(cout));
                CheckWeek.setText(TimeConverter.week.format(cout));

                String string = "Transaction ID : " + invoice.getTransactionID() + "  Date : " + invoice.getTransactionDate();
                Transaction.setText(string);
                ID.setText("Package ID : " + invoice.getTravelPackageID());
                String ruppe = "₹ " + invoice.getAmount();
                TotalPrice.setText(ruppe);
                BasePrice.setText(ruppe);
                Taxes.setText("₹ 0");
                NumDays.setText(invoice.getNumDays() + " Night(s)");
                NumPersons.setText("Number of person = " + invoice.getNumPerson());
            }catch (Exception e){
                System.out.println();
            }
        }
    }
}
