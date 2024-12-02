package com.example.login1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {
    private List<Usuario> usuarioList;

    public UsuarioAdapter(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, parent, false);
        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = usuarioList.get(position);
        holder.tvNombre.setText(usuario.getNombre());
        holder.tvGenero.setText(usuario.getGenero());
        holder.tvFechaNacimiento.setText(usuario.getFechaNacimiento());
        holder.tvUsuario.setText(usuario.getUsuario());
        holder.tvContraseña.setText(usuario.getContraseña());
        holder.tvTelefono.setText(usuario.getTelefono());
        holder.tvNivelEstudios.setText(usuario.getNivelEstudios());
        holder.tvIntereses.setText(usuario.getIntereses());
        // Configura más TextViews según los datos que desees mostrar
    }

    @Override
    public int getItemCount() {
        return usuarioList.size();
    }

    public static class UsuarioViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre, tvGenero, tvFechaNacimiento, tvNivelEstudios, tvTelefono, tvUsuario, tvContraseña, tvIntereses;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvGenero = itemView.findViewById(R.id.tvGenero);
            tvFechaNacimiento = itemView.findViewById(R.id.textViewFechaNacimiento);
            tvNivelEstudios = itemView.findViewById(R.id.textViewNivelEstudios);
            tvTelefono = itemView.findViewById(R.id.textViewTelefono);
            tvUsuario = itemView.findViewById(R.id.textViewUsuario);
            tvContraseña = itemView.findViewById(R.id.textViewContraseña);
        }
    }
}

