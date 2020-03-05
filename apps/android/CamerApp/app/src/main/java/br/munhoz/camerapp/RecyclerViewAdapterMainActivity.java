package br.munhoz.camerapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Console;
import java.util.List;

/**
 * Created by Marco Aurélio on 03/01/2017.
 */

public class RecyclerViewAdapterMainActivity extends RecyclerView.Adapter<RecyclerViewAdapterMainActivity.ViewHolder> {


    private List<Camera> itens;
    private IRecyclerViewOnClickListener onClickListener;
    private boolean cardViewContextMenuListener = false;

    public RecyclerViewAdapterMainActivity(List<Camera> itens, IRecyclerViewOnClickListener onClickListener) {
        this.itens = itens;
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerViewAdapterMainActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_main_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapterMainActivity.ViewHolder holder, final int position) {
        Camera item = itens.get(position);
        holder.txtNome.setText(item.nome);
        holder.txtEndereco.setText(item.endereco + ":" + item.porta);

        holder.cardView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuItem menuAlterar = menu.add(Menu.NONE, 1, Menu.NONE, "Alterar");
                menuAlterar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        onClickListener.onMenuAlterarClick(holder.itemView, position);
                        return true;
                    }
                });
                MenuItem menuExcluir = menu.add(Menu.NONE, 2, Menu.NONE, "Excluir");
                menuExcluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        onClickListener.onMenuExcluirClick(holder.itemView, position);
                        return true;
                    }
                });
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                v.showContextMenu();
                return true;
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener != null) {
                    //clique normal
                    onClickListener.onClick(holder.itemView, position);
                }
            }
        });

        if (item._imagem!=null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(item._imagem, 0, item._imagem.length);
            holder.imgCamera.setImageBitmap(bmp);
            holder.imgCamera.setVisibility(View.VISIBLE);
            holder.frameProgressBar.setVisibility(View.GONE);
        } else {
            holder.imgCamera.setVisibility(View.GONE);
            holder.frameProgressBar.setVisibility(View.VISIBLE);
            String urlBaseSnapshot = "http://" + item.endereco + ":" + item.porta + "/snapshot.cgi?user=" + item.usuario + "&pwd=" + item.senha + "&t=0";
            new LoadImage(holder.imgCamera, holder.frameProgressBar, item).execute(urlBaseSnapshot);
        }
    }

    class LoadImage extends AsyncTask<String,Void,byte[]> {

        private ImageView imageView;
        private FrameLayout frameLayout;
        private Camera item;

        public LoadImage(ImageView imageView, FrameLayout frameLayout, Camera item) {
            this.imageView = imageView;
            this.frameLayout = frameLayout;
            this.item = item;
        }

        @Override
        protected byte[] doInBackground(String... params) {
            return HttpClient.doGet(params[0]);
        }

        @Override
        protected void onPostExecute(byte[] result) {
            if (result != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(result, 0, result.length);
                imageView.setImageBitmap(bmp);
            }
            item._imagem = result;
            imageView.setVisibility(View.VISIBLE);
            frameLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itens != null ? itens.size() : 0;
    }

    public Camera getItem(int position) {
        return itens.get(position);
    }

    //---------------

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public ImageView imgCamera;
        public FrameLayout frameProgressBar;
        public TextView txtNome;
        public TextView txtEndereco;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            imgCamera = (ImageView) itemView.findViewById(R.id.imgCamera);
            frameProgressBar = (FrameLayout) itemView.findViewById(R.id.frameProgressBar);
            txtNome = (TextView) itemView.findViewById(R.id.txtNome);
            txtEndereco = (TextView) itemView.findViewById(R.id.txtEndereco);
        }
    }

}
