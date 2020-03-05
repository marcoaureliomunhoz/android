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