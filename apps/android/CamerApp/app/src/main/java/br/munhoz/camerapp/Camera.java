package br.munhoz.camerapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Marco Aurélio on 03/01/2017.
 */

public class Camera implements Parcelable {

    int codigo = 0;
    String nome = "";
    String endereco = "";
    String porta = "";
    String usuario = "";
    String senha = "";
    byte[] _imagem = null;

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(codigo);
        parcel.writeString(nome);
        parcel.writeString(endereco);
        parcel.writeString(porta);
        parcel.writeString(usuario);
        parcel.writeString(senha);
        parcel.writeByteArray(_imagem);
    }

    public static final Parcelable.Creator<Camera> CREATOR = new Parcelable.Creator<Camera>() {
        public Camera createFromParcel(Parcel in) {
            return new Camera(in);
        }

        public Camera[] newArray(int size) {
            return new Camera[size];
        }
    };

    private Camera(Parcel in) {
        codigo = in.readInt();
        nome = in.readString();
        endereco = in.readString();
        porta = in.readString();
        usuario = in.readString();
        senha = in.readString();
        in.readByteArray(_imagem);
    }

    public Camera() {
    }
}
