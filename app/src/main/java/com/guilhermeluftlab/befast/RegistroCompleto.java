package com.guilhermeluftlab.befast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.guilhermeluftlab.befast.controllers.ControllerUser;
import com.guilhermeluftlab.befast.models.Endereco;
import com.guilhermeluftlab.befast.models.Mask;
import com.guilhermeluftlab.befast.models.Usuario;
import com.guilhermeluftlab.befast.models.endereco.Util;
import com.guilhermeluftlab.befast.models.endereco.ZipCodeListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroCompleto extends AppCompatActivity {
    public static final int IMAGEM_INTERNA = 1;
    static int PReqCode = 1;
    static int REQUESTCODE = 1;
    private EditText nome;
    private EditText senha;
    private EditText email;
    private EditText numCelular;
    private EditText dataNascimento;
    private EditText cep;
    private EditText rua;
    private EditText bairro;
    private EditText numeroResidencia;
    private EditText complemento;
    private EditText cidade;
    private EditText estado;
    private Spinner spinner;
    private Button esqueciCep;
    private Button pegarProfPic;
    private ImageView perfil;
    private ArrayAdapter adapter;
    private Util util;
    private Button finalizar;
    private String pathImg;
    private String resposta;
    private Uri selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_registro_completo);


        //Dados Cliente
        perfil =  findViewById(R.id.regComFotoPerfil);
        nome =  findViewById(R.id.regCompNome);
        senha =  findViewById(R.id.regCompSenha);
        email =  findViewById(R.id.regCompEmail);
        numCelular =  findViewById(R.id.editTextPhone);
        dataNascimento =  findViewById(R.id.regCompDataNascimento);
        cep =  findViewById(R.id.regCompCEP); //etZipCode
        rua =  findViewById(R.id.regCompLogradouro);
        numeroResidencia =  findViewById(R.id.regCompNumeroCasa);
        bairro =  findViewById(R.id.regCompBairro);
        complemento =  findViewById(R.id.regCompComplemento);
        cidade =  findViewById(R.id.regCompCidade);
        estado =  findViewById(R.id.regCompUf);

        spinner =  findViewById(R.id.regCompSexo);
        esqueciCep =  findViewById(R.id.buttonEsqueciCep);
        finalizar = findViewById(R.id.buttonEditarCadastro);

        //Criando o Spinner e adicionando as strings
        adapter = ArrayAdapter.createFromResource(this, R.array.sexo_usuario, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        //adicionando cep
        cep.addTextChangedListener(new ZipCodeListener(this));

        //travar campos enquanto busca CEP
        util = new Util(this, R.id.regCompLogradouro,
                R.id.regCompBairro,
                R.id.regCompComplemento,
                R.id.regCompCidade,
                R.id.regCompUf,
                R.id.buttonEsqueciCep);

        //Adicionando MASK
        dataNascimento.addTextChangedListener(Mask.insert(Mask.DATANASCIMENTO_MASK, dataNascimento));
        numCelular.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK, numCelular));
        nome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0){
                    nome.setError("Preencha todos os campos, por favor!");
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (email.getText().toString() != null && email.getText().length() > 0) {
                    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
                    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
                    Matcher matcher = pattern.matcher(email.getText().toString());
                    if (!matcher.matches()) {
                        email.setError("Insira um email valido!");
                    }
                }

            }
        });
        senha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() < 6){
                    senha.setError("Senha precisa ter ao menos 6 dígitos!");
                }
            }
        });

        //Buscar Imagem
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= 22){
                    checkAndRequestForPermission();
                }
                else{
                    openGallery();
                }

            }
        });


        //Buscando cep
        esqueciCep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent consultarCep = new Intent(Intent.ACTION_VIEW);
                consultarCep.setData(Uri.parse("https://buscacepinter.correios.com.br/app/endereco/index.php?t"));
                startActivity(consultarCep);

            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControllerUser.getInstance().saveUser(setUsuario());
                Toast.makeText(getApplicationContext(), R.string.salvar_ok, Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), UsuarioLogado.class));

            }
        });


    }

    private void openGallery() {
        //TODO: open gallery intent and wait for user to pick an image!
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESTCODE);

    }

    private void checkAndRequestForPermission() {

        if(ContextCompat.checkSelfPermission(RegistroCompleto.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(RegistroCompleto.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(RegistroCompleto.this, "Por favor, aceite!", Toast.LENGTH_SHORT). show();
            }
            else{
                ActivityCompat.requestPermissions(RegistroCompleto.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PReqCode);
            }
        }
        else{
            openGallery();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == REQUESTCODE && data != null){
                selectedImage = data.getData();
                perfil.setImageURI(selectedImage);
                Toast.makeText(getApplicationContext(), selectedImage.toString(), Toast.LENGTH_SHORT).show();
            }


    }

    public void lockFields(boolean isToLock){
        util.lockFields(isToLock);
    }

    public String getUriZipCode(){
        return "https://viacep.com.br/ws/"+ cep.getText().toString() + "/json/";
    }


    public void setDataViews(Endereco endereco){
        setField(R.id.regCompLogradouro, endereco.getLogradouro());
        setField(R.id.regCompBairro, endereco.getBairro());
        setField(R.id.regCompComplemento, endereco.getComplemento());
        setField(R.id.regCompCidade, endereco.getLocalidade());
        setField(R.id.regCompUf, endereco.getUf());


    }



    private void setField(int id, String data){
        ((EditText) findViewById(id)).setText(data);

    }

    public Usuario setUsuario(){
        Usuario usuario = new Usuario();
        Endereco endereco = new Endereco();

        //Usuario
        usuario.setFotoPerfil(selectedImage.toString());
        usuario.setNome(nome.getText().toString());
        usuario.setEmail(email.getText().toString());
        usuario.setSenha(senha.getText().toString());
        usuario.setSexo((String) spinner.getSelectedItem());
        usuario.setNumCelular(numCelular.getText().toString());
        usuario.setDataNascimento(dataNascimento.getText().toString());

        //Endereco
        endereco.setCep(cep.getText().toString());
        endereco.setLogradouro(rua.getText().toString());
        endereco.setComplemento(complemento.getText().toString());
        endereco.setBairro(bairro.getText().toString());
        endereco.setLocalidade(cidade.getText().toString());
        endereco.setUf(estado.getText().toString());

        //Finalizando
        usuario.setEndereco(endereco);

        return usuario;
    }

    private void opcoesErro(String resposta){

        if(resposta.contains("least 6 characters"))
            Toast.makeText(getApplicationContext(), R.string.senha_pequena, Toast.LENGTH_LONG).show();

        else if(resposta.contains("address is badly"))
            Toast.makeText(getApplicationContext(), R.string.badly_email, Toast.LENGTH_LONG).show();

        else if(resposta.contains("interrupted connection"))
            Toast.makeText(getApplicationContext(), R.string.conexao_perdida, Toast.LENGTH_LONG).show();

        else
            Toast.makeText(getApplicationContext(), resposta, Toast.LENGTH_LONG).show();

    }


}