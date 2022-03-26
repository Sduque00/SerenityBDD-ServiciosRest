Feature: crear job
    como automatizador
    quiero validar el funcionamiento de crear
    para crear nuevo trabajo en el usuario

  Scenario: Crear
    Given que el usuario esta en el recurso web indicando nombre "morpheus" y trabajo "leader"
    When realizo la peticion de crear
    Then obtendre un codigo de respuesta exitoso