package com.example.user_manager_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter for displaying a list of candidates in a RecyclerView.
 *
 * @author Aryan Rao
 */
public class CandidateListAdapter extends RecyclerView.Adapter<CandidateListAdapter.ViewHolder> {

    private Context context;
    private List<Candidate> candidates;

    /**
     * Constructor for the adapter.
     *
     * @param context The context in which the adapter is used.
     * @param candidates The list of candidates to display.
     */
    public CandidateListAdapter(Context context, List<Candidate> candidates) {
        this.context = context;
        this.candidates = candidates;
    }

    /**
     * Sets the list of candidates to display.
     *
     * @param candidates The new list of candidates.
     */
    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    /**
     * Creates a new ViewHolder for displaying candidate information.
     *
     * @param parent The parent ViewGroup.
     * @param viewType The type of view to create.
     * @return A new ViewHolder instance.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_candidate, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Binds the candidate information to the ViewHolder.
     *
     * @param holder The ViewHolder to bind the information to.
     * @param position The position of the candidate in the list.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Candidate candidate = candidates.get(position);
        holder.candidateName.setText(candidate.getName());
        holder.candidateBio.setText(candidate.getBio());
    }

    /**
     * Returns the number of candidates in the list.
     *
     * @return The number of candidates in the list.
     */
    @Override
    public int getItemCount() {
        return candidates.size();
    }

    /**
     * ViewHolder class for displaying candidate information.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView candidateName;
        private TextView candidateBio;

        /**
         * Constructor for the ViewHolder.
         *
         * @param itemView The View for the ViewHolder.
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            candidateName = itemView.findViewById(R.id.candidate_name);
            candidateBio = itemView.findViewById(R.id.candidate_bio);
        }
    }
}
