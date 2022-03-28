Feature: Consultar recurso
    Como tester de la api
    necesito consultar los recursos de los usuarios
    para validar la existencia en el sistema

  Scenario: Consultar recurso
    Given que estoy en el recurso web
    When realizo la peticion de consultar recurso
    Then obtendre un codigo de respuesta y el recurso

  Scenario: Recurso no encontrado
    Given que estoy en servicio web
    When realizo la peticion de consultar recurso con otra url
    Then obtendre un codigo de respuesta erroneo