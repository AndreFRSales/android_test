# Worldpackers Android

## explicação

Temos aqui uma listagem de filmes, bem simples.
O `GetMovies.kt` está simulando um request para uma API e retornando uma lista, que é passada para o presenter e exibida na tela.

Faça um fork desse projeto e resolva os desafios.

Os desafios são o seguinte:

1 - Fazer uma `View` do item do filme, do jeito que vc achar que fica melhor (obs: a view já existe, e é necessário que esta view use o `databinding`);

2 - Implementar o load de imagem no imageview usando o `BindingAdapter`, a classe já existe só precisa ser implementada.

3 - Escrever os `tests` para garantir que o `Presenter` do item de filmes funciona corretamente (o arquivo já existe e tem uns exemplos, dê uma olhada na pasta de tests);

4 - No `Presenter` de filmes há os métodos para exibir um filtro por categorias e filtrar a lista por categorias, implemente esses métodos (não é necessário add uma view de filtro, mas se você quiser muito fique a vontade);

5 - Escrever os `tests` para garantir o funcionamento dos filtros (o arquivo de test tbm já existe com um test de exemplo que está quebrando);

ps: os tests estão escritos em `Spek` e usam `Kluent` para as assertions, se você não estiver confortável em utilizar eles pode usar junit normalmente.

:shipit:
### bom teste <3 
