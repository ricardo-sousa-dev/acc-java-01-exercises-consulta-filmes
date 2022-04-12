package com.trybe.consultafilmes;

// import java.util.Set;
import java.util.Map;
import java.util.Set;

public class Principal {

  /**
   * Função utilizada apenas para validação da solução do desafio.
   *
   * @param args Não utilizado.
   */
  public static void main(String[] args) {
    Consultas consultas = new Consultas(Filmes.todos());
    Map<String, Set<Filme>> resultados = consultas.filmesLancadosNoAnoAgrupadosPorCategoria(2021);
    System.out.println(resultados);
  }
}
