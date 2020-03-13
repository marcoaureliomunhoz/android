# Android

**Versões:**

- Android 11
- Android 10 ou Android Q - API 29
    - Melhoramento na API de localização;
    - Modo anônimo do Google Maps;
    - Sistema de gestos aperfeiçoado;
    - Novos recursos de privacidade, como permissões e restrições mais rígidas (https://developer.android.com/about/versions/10/privacy);
        - Apps que usam APIs de localização em segundo plano pode ser afetados;
        - Apps que usam APIs de Wi-Fi e Bluetooth podem ser afetados;
        - Apps que iniciam atividades em segundo plano sem interação do usuário podem ser afetados;
    - Telas para foldables: os smartphones dobráveis tem telas ajustadas de forma automática;
    - Compatibilidade com 5G;
    - Vulkan 1.1;
    - NNAPI 1.2;
- 9.0 - API 28 - Pie
    - Janelas e toda a interface arredondada para combinar com o novo padrão de design dos produtos Google;
    - Suporte para telas notch (https://tecnoblog.net/243857/notch-o-que-e-lista-celulares);
    - Suporte ao novo protocolo DNS over TLS que visa oferecer mais segurança e privacidade nas conexões;
    - Sistema "Shush" que ativa o não perturbe quando você coloca o celular com a tela para baixo;
    - Previsão de bateria adaptativa;
- 8.0 e 8.1 - API 26 e 27 - Oreo
    - Nova arquitetura que deixa o desenvolvimento de hardware para Android mais fácil e veloz;
    - Suporte ao recurso picture-in-picture (https://developer.android.com/about/versions/oreo/android-8.0#opip);
    - Suporte ao Unicode 10 de emojis;
    - Limites aos apps rodando em segundo plano;
    - Google Play Protect;
    - API de redes neurais (sistema aprende com o seu uso diário);
    - Melhorias para WebView (https://developer.android.com/guide/webapps/managing-webview);
- 7.0 e 7.1 - API 24 e 25 - Nougat
    - Multi-janelas (https://developer.android.com/about/versions/nougat/android-7.0#multi-window_support);
    - Suporte nativo para tela dividida;
    - Permite adicionar informações de emergência à tela de bloqueio;
    - Sensor de impessão digital para abrir/fechar a barra de notificações;
    - Permite responder mensagens a partir da tela de bloqueio, pela própria notificação;
    - Inicialização direta (https://developer.android.com/training/articles/direct-boot);
    - Nova API de renderização, a Vulkan 3D;
    - Modo de realidade virtual DayDream;
    - Melhorias para WebView (https://developer.android.com/about/versions/nougat/android-7.0#webview);
- 6.0 - API 23 - Marshmallow
    - Introduziou o modo "Não Perturbe" que pode ser usado principalmente em reuniões para não ser interrompido por notificações;
    - Agora os apps podem solicitar permissões individuais e em tempo de execução. Antes o usuário decidia tudo ao instalar. Agora o usuário pode aceitar certas permissões e negar outras durante o uso do app;
    - Modos "Soneca" e "App em Espera". Estes modos foram introduzidos com objetivo de otimizar a energia. Estes novos modos podem afetar os apps que realizam atividades em segundo plano (https://developer.android.com/training/monitoring-device-state/doze-standby);
    - Suporte ao Android Pay ou Google Pay;
    - Google Now;
- 5.0 e 5.1 - API 21 e 22 - Lollipop
    - Nova aparência baseada no Material Design;
    - Acesso à notificações na tela de bloqueio;
    - Suporte nativo para chamadas Wifi;
    - Suporte para CPUs de 64 bits;
    - OpenGL ES 3.1;
    - Lanterna nativa;
- 4.4 - API 19 - KitKat
    - Suporte para smartwatches (4.4W);
    - Android Runtime (ART), novo ambiente de execução experimental, substituindo a máquina virtual Dalvik;
- 4.1 a 4.3 - API 16 a 18 - Jelly Bean
    - Melhorias nas opções de notificação, incluindo notificações personalizadas;
    - Gestos;
    - Suporte para resolução 4K;
- 4.0 - API 15 - Ice Cream Sandwich
    - Captura de tela;
    - Aplicativos podem ser abertos a partir da tela de bloqueio;
    - Desbloquio facial;
    - NFC;
- 3.0 - Honeycomb
    - Suporte para tablets;
- 2.3 - API 10 - Gingerbread
    - NFC;
- 2.2 - API 8 - Froyo
    - Notificações push;
    - Suporte para telas HD 720p de até 4' e até 320 PPI;
- 2.1 - API 5 - Eclair
- 1.6 - API 4 - Donut
- 1.5 - API 2 - Cupcake
- 1.0 - API 1 - Petit Four

**SuportLibrary vs AndroidX**

Toda vez que o Google lança uma nova versão do Android esta nova versão vem com bibliotecas nativas para as novas funcionalidades, ou seja, as bibliotecas fazem parte do sistema operacional.

Durante muito tempo o Google foi lançando bibliotecas de compatibilidade para permitir que usuários em versões antigas do Android usufruissem das funcionalidades disponíveis nas novas versões. Para que seja possível executar um app em várias versões do sistema operacional as bibliotecas de suporte são fundamentais e essas biblitecas são carregadas junto ao APK do app.

Recentemente o Google criou o AndroidX, uma nova hierarquia de pacotes (bibliotecas) que facilita o suporte a novas funcionalidades em sistemas operacionais antigos. O AndroidX é a nova forma que o Google enctontrou para que usuários em versões antigas possam aproveitar as funcionalidades das novas versões do Android. O AndroidX apresentará nomes de pacotes simplificados, bem como IDs de grupos e artefatos do Maven que melhor refletem o conteúdo de cada pacote e os níveis de API suportados.

Ao contrário da biblioteca de suporte, os pacotes AndroidX são mantidos e atualizados separadamente. Os pacotes androidx são versionados por Semantic Versioning, ou seja, seguem o modelo de versionamento da maioria das bibliotecas modernas de hoje (inclusive ela começa da versão 1.0.0). Você pode atualizar as bibliotecas do AndroidX em seu projeto de forma independente, ou seja, você pode ir subindo a versão do AndroidX, que por sua vez vai evoluindo conforme necessário.

**Jetifier:**

Trata-se de uma ferramenta que converte os pacotes de suporte de dependência de nossos apps para pacotes equivalentes dentro do AndroidX. 

Para usar o AndroidX em um projeto, é preciso configurar o targetSdkVersion do projeto para 28 e adicionar as 2 linhas a seguir no arquivo gradle.properties.

<pre>
android.useAndroidX=true
android.enableJetifier=true
</pre>

**Recursos:**

- **Drawable:**
    - https://developer.android.com/guide/topics/graphics/drawables
    - https://developer.android.com/guide/topics/resources/drawable-resource
    - https://developer.android.com/guide/topics/graphics/vector-drawable-resourcesddd
    - https://guides.codepath.com/android/drawables
    - https://www.vogella.com/tutorials/AndroidDrawables/article.html

Plataformas para criação de drawable/shapes:

- https://angrytools.com/android/button/

Exemplo de um drawable/shape (shape_orange_button.xml) com fundo laranja e cantos arredondados para background de botões:

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android" android:shape="rectangle">
    <solid android:color="@android:color/holo_orange_dark" />
    <corners android:radius="10dp" />
</shape>
```

<img src="assets/drawable_shape_orange_button.png">

Aplicando o drawable/shape no background do botão:

```xml
<Button
    android:id="@+id/btnNew"
    android:text="New"
    ...
    android:background="@drawable/shape_orange_button"
    ... />
```

**Ciclo de Vida de uma Activity:**

<img src="assets/activity_lifecycle.png">

- https://developer.android.com/reference/android/app/Activity#activity-lifecycle

**ListView vs RecyclerView:**

O RecyclerView é um novo ListView. O RecyclerView é recomendado como boa prática e foi introduzido junto ao Material Design. As principais vantagens do RecyclerView são:

- Suporte a animações ao adicionar ou remover elementos da lista;
- Controle automático de reutilização das views (padrão ViewHolder);
    - ViewHolder: a ideia é manter/reaproveitar as views já criadas e trocar apenas o conteúdo. Esse recurso otimiza a rolagem da listview, principalmente quando temos muitos registros. Na ListView tradicional o desenvolvedor tem que fazer todo o controle de reciclagem/reaproveitamento. No RecyclerView esse controle é automático e transparente ao desenvolvedor.
- Novo gerenciador de layout e de renderização de views como listas, grids ou outra forma customizada;

O RecyclerView continua usando o conceito de adapters para preencher o conteúdo da lista.

https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html

**Serializable vs Parcelable:**

Para transeferir objetos de uma activity para outra através de uma intent podemos usar a interface Serializable. Caso você esteja transferindo objetos simples, use Serializable mesmo, pois é mais simples também. Porém se estiver transferindo objetos complexos e com muita informação utilize Parcelable, pois apesar de ser mais trabalhoso de implementar, esta nova opção é bem mais otimizada e economiza muito mais memória durante a transferência.

https://medium.com/@lucas_marciano/por-que-usar-o-parcelable-ao-inv%C3%A9s-do-serializable-5f7543a9c7f3

**Tipos de Dialogs:**

- AlertDialog: dialog específico para informar ou questionar o usuário;
- DatePickerDialog: dialog para obter data;
- TimerPickerDialog: dialog para obter hora;
- DialogFragment: container para criar dialogs customizados;

https://developer.android.com/guide/topics/ui/dialogs  

**Usando alguns dos recursos da linguagem Java 8:**

Adicione no arquivo build.grade da aplicação

```java
android {
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}
```

https://developer.android.com/studio/write/java8-support  
https://www.alura.com.br/artigos/utilizando-features-do-java-8-no-android

**Obtendo resultado de uma activity:**

Quando enviamos uma intent de uma activity A para uma activity B temos a opção de enviar um código (requestCode).

```java
// ActivityA.class

final int REQUISITON_CODE_TO_ANY_ACTION = 1;

private void abrirActivityB() {
    Intent intent = new Intent(this, ActivityB.class);
    intent.putExtra("name", "Marco");
    startActivityForResult(intent, REQUISITON_CODE_TO_ANY_ACTION);
}
```

Quando a activity B é desempilhada e a activity A entra em cena temos a opção de receber um resultado enviado pela activity B:

```java
// ActivityB.class

final int REQUISITON_CODE_TO_ANY_ACTION = 1;
final string name;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout...);

    Intent intent = getIntent();
    name = intent.hasExtra("name")
        ? intent.getSerializableExtra("name")
        : "";
}

private void finalizarB() {
    Intent intent = new Intent();
    intent.putExtra("name", name);
    intent.putExtra("size", name.size());
    setResult(Activity.OK, intent);
    finish();
}
```

```java
// ActivityA.class

final int REQUISITON_CODE_TO_ANY_ACTION = 1;

@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    // uma boa prática é primeiramente verificar qual foi a requisição realizada
    if(requestCode == REQUISITON_CODE_TO_ANY_ACTION){

        // depois, aqui podemos executar alguma ação independente do resultado

        // depois, aqui podemos verificar o resultado e executar alguma ação conforme este
        if(resultCode == Activity.RESULT_OK) {
            // aqui podemos realizar alguma ação em caso de resultado ok
        } else if (resultCode == Activity.RESULT_CANCELED){
            // aqui podemos realizar alguma ação em caso de cancelamento
        }

    }
}
```

**Opções para persitência de dados:**

- **SharedPreferencies:** recurso de persistência "chave-valor". Este é mais indicado para armazenar preferências do usuário. (https://developer.android.com/training/data-storage/shared-preferences)
- **Banco de dados Room:** o Room é uma camada de abstração sobre o SQLite. Com ele fica mais fácil a persistência de dados no dispositivo em um banco SQLite. (https://developer.android.com/training/data-storage/room)

---

**Referências:**

- https://developer.android.com
- https://developer.android.com/about
- https://developer.android.com/about/dashboards
- https://developer.android.com/docs
- https://pt.wikipedia.org/wiki/Android
- https://www.oficinadanet.com.br/post/13939-a-historia-do-android
- https://medium.com/@mppagano/tamanho-das-telas-dos-celulares-uxdrops-7b33757187c3
- https://stackoverflow.com/questions/51280090/what-is-androidx
- https://medium.com/@paulo_linhares/androidx-adeus-support-library-584b64bf56ef
- https://stackoverflow.com/questions/51680671/what-is-jetifier/52518822#52518822
- https://www.androidauthority.com/android-jetpack-android-support-library-878587/
- https://developer.android.com/jetpack/docs/getting-started
- https://developer.android.com/jetpack/androidx/versions
- https://www.alura.com.br/artigos/salvando-informacoes-com-o-shared-preferences
- Livro Google Android (Ricardo R. Lecheta)