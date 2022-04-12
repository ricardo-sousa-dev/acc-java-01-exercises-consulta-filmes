package com.trybe.consultafilmes;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Consultas {

  private final Collection<Filme> filmes;

  public Consultas(Collection<Filme> filmes) {
    this.filmes = filmes;
  }

  /**
   * Consulta 1: a partir da coleção de filmes desta classe, este método retorna o conjunto de
   * atores que interpretaram a si próprios em pelo menos um desses filmes.
   *
   * <p>Considera-se "atores que interpretaram a si próprios" aqueles que têm o seu nome como uma
   * das chaves do Map `atoresPorPersonagem` e também como um dos itens pertencentes ao conjunto
   * associado a esta mesma chave.
   */
  public Set<String> atoresQueInterpretaramSiProprios() {
    return filmes.stream()
        .flatMap(film -> film.atoresPorPersonagem.entrySet().stream())
        .filter(e -> e.getValue().contains(e.getKey()))
        .map(Map.Entry::getKey)
        .collect(Collectors.toUnmodifiableSet());
    // stream() -> flatMap() -> filter() -> map() ->
    // collect(Collectors.toUnmodifiableSet())
    // stream() -> substitui um iterador como for ou while
    // flatMap() -> coloca todos os items de stream de streams no mesmo nível de
    // processamento.
    // entrySet -> retorna uma visualização de conjunto de todos os mapeamentos
    // [chave,valor,...].
    // filter() -> filtra o set gerado, chave que está entre os valores.
    // map() -> transforma o set gerado, chave que está entre os valores.
    // Map.Entry -> retorna um objeto que representa um par de chave e valor.
    // getKey() -> retorna a chave do par.
    // collect(Collectors.toUnmodifiableSet()) -> retorna um set com os valores.
    // https://medium.com/@racc.costa/streams-no-java-8-e-no-java-9-36177c5c3313
  }

  public static void main(String[] args) {
    List<Filme> filmes = emptyList();
    Consultas consultas = new Consultas(filmes);
    System.out.println(consultas.atoresQueInterpretaramSiProprios());
  }

  /**
   * Consulta 2: a partir da coleção de filmes desta classe, este método retorna a lista de atores
   * que atuaram em pelo menos um filme de um determinado diretor. A lista retornada está disposta
   * em ordem alfabética.
   *
   * <p>Considera-se que um ator tenha atuado em um filme de um determinado diretor se ele tem o seu
   * nome como um dos itens do campo `atores`, ao mesmo tempo em que o diretor em questão tem o seu
   * nome como um dos itens do campo `diretores` do mesmo filme.
   */
  public List<String> atoresQueAtuaramEmFilmesDoDiretorEmOrdemAlfabetica(String diretor) {
    return filmes.stream()
        .filter(film -> film.diretores.contains(diretor))
        .flatMap(film -> film.atores.stream())
        .sorted(Comparator.comparing(String::toString))
        .distinct()
        .collect(Collectors.toUnmodifiableList());
    // stream() -> filter() -> flatMap() -> sorted() ->
    // collect(Collectors.toUnmodifiableList())
    // stream() -> substitui um iterador como for ou while
    // filter() -> filtra o set gerado, chave que está entre os valores.
    // flatMap() -> coloca todos os items de stream de streams no mesmo nível de
    // processamento.
    // sorted() -> ordena o set gerado, chave que está entre os valores.
    // collect(Collectors.toUnmodifiableList()) -> retorna um set com os valores.
    // https://

    // ALTERNATIVA COM FOR
    // List<Set<String>> filme = this.filmes.stream()
    // .filter(d -> d.diretores.contains(diretor))
    // .map(a -> a.atores)
    // .collect(Collectors.toList());
    // ArrayList<String> atores = new ArrayList<>();
    // for (Set<String> a : filme) {
    // for (String b : a) {
    // if (!atores.contains(b)) {
    // atores.add(b);
    // }
    // }
    // }
    // System.out.println(atores);
    // List<String> test = atores.stream()
    // .sorted(Comparator.comparing(String::toString))
    // .collect(Collectors.toList());

  }

  /**
   * Consulta 3: a partir da coleção de filmes desta classe, este método retorna a lista de filmes
   * em que pelo menos um dos diretores tenha atuado. A lista retornada está disposta em ordem de
   * lançamento, com os filmes mais recentes no início.
   *
   * <p>Considera-se "filmes em que pelo menos um dos diretores tenha atuado" aqueles em que pelo
   * menos um dos itens do campo `diretores` também é um item do campo `atores`.
   */
  public List<Filme> filmesEmQuePeloMenosUmDiretorAtuouMaisRecentesPrimeiro() {
    return filmes.stream()
        .filter(
            film -> film.diretores.stream().anyMatch(director -> film.atores.contains(director)))
        .sorted(Comparator.comparing(Filme::anoDeLancamento).reversed())
        .distinct()
        .collect(Collectors.toUnmodifiableList());
    // stream() -> filter() -> sorted() ->
    // collect(Collectors.toUnmodifiableList())
    // stream() -> substitui um iterador como for ou while
    // filter() -> filtra o set gerado, chave que está entre os valores.
    // sorted() -> ordena o set gerado, chave que está entre os valores.
    // collect(Collectors.toUnmodifiableList()) -> retorna um set com os valores.
    // https://receitasdecodigo.com.br/java/java-8-stream-anymatch-com-exemplos
  }

  /**
   * Consulta 4: a partir da coleção de filmes desta classe, este método retorna um Map contendo
   * todos os filmes lançados em um determinado ano agrupados por categoria.
   *
   * <p>Cada chave do Map representa uma categoria, enquanto cada valor representa o conjunto de
   * filmes que se encaixam na categoria da chave correspondente.
   */
  public Map<String, Set<Filme>> filmesLancadosNoAnoAgrupadosPorCategoria(int ano) {
    return emptyMap(); // TODO: Implementar (bônus).
  }
}
