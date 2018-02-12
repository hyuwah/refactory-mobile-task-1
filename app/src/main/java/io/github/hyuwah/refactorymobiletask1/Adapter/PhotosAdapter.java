package io.github.hyuwah.refactorymobiletask1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import io.github.hyuwah.refactorymobiletask1.Model.Photo;
import io.github.hyuwah.refactorymobiletask1.R;
import java.util.List;

/**
 * Created by hyuwah on 07/02/18.
 */

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

  private List<Photo> photoList;
  private Context mContext;

  // Declare clickListener
  private OnItemClickListener clickListener;

  // Define interface for click listener
  public interface OnItemClickListener {

    void onItemClick(View itemView, int position);
  }

  // Define setter for click listener
  public void setOnItemClickListener(OnItemClickListener clickListener) {
    this.clickListener = clickListener;
  }


  // Constructor
  public PhotosAdapter(Context mContext, List<Photo> photoList) {
    this.photoList = photoList;
    this.mContext = mContext;
  }


  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = LayoutInflater.from(parent.getContext()).inflate(
        R.layout.list_item_photo, parent, false);
    return new ViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(PhotosAdapter.ViewHolder holder, int position) {

    Photo currentPhoto = photoList.get(position);

    // Assign value dari object photo ke elemen view (holder)
    holder.tvAlbumId.setText("Album Id : " + Integer.toString(currentPhoto.getAlbumId()));
    holder.tvId.setText("Id : " + Integer.toString(currentPhoto.getId()));
    holder.tvTitle.setText(currentPhoto.getTitle());

    // Fetch Image from url with picasso
    // Check takutnya link url image nya kosong
    if (!TextUtils.isEmpty(currentPhoto.getThumbnailUrl())) {
      Picasso.with(mContext)
          .load(currentPhoto.getThumbnailUrl())
          .placeholder(R.drawable.placeholder_img)
          .fit()
          .into(holder.ivThumbnail);
    }

  }

  @Override
  public int getItemCount() {
    return (photoList != null ? photoList.size() : 0);
  }

  /**
   * Inner class ViewHolder
   */
  public class ViewHolder extends RecyclerView.ViewHolder {

    // Variable
    ImageView ivThumbnail;
    TextView tvAlbumId, tvId, tvTitle;

    // Constructor

    public ViewHolder(final View itemView) {
      super(itemView);
      this.ivThumbnail = itemView.findViewById(R.id.photo_image);
      this.tvAlbumId = itemView.findViewById(R.id.photo_album_id);
      this.tvId = itemView.findViewById(R.id.photo_id);
      this.tvTitle = itemView.findViewById(R.id.photo_title);

      // Setup click listener
      itemView.setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View view) {
          if (clickListener != null) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
              clickListener.onItemClick(itemView, position);
            }
          }
        }
      });

    }

  }
}



