Feature: crear job
    como automatizador
    quiero validar el funcionamiento de crear
    para crear nuevo trabajo en el usuario

  Background:
    Given que el usuario esta en el recurso web indicando nombre "morpheus" y trabajo "leader"

  Scenario: Crear
    When realizo la peticion de crear
    Then obtendre un codigo de respuesta exitoso

  Scenario: Intentar crear usuario con diferente content-type
    When envio la peticion, pero con un contenttype de texto
    Then se creara un nuevo registro con solo el campo id