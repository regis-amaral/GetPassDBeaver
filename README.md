# GetPass DBeaver

O programa recupera a senha das conexões e as exibe juntamente com os dados de cada conexão.

Muito útil no caso de esquecimento de uma senha salva apenas no DBeaver.

Só funciona na versão Community e em outra versão sem que a senha mestra tenha sido definida nas configurações do DBeaver.

Para saber mais sobre segurança no armazenamento de senhas do DBeaver acesse os links abaixo:

- [DBeaver Documentation - Project security](https://dbeaver.com/docs/wiki/Project-security/)

- [DBeaver Documentation - Admin Gerenciar Conexões](https://dbeaver.com/docs/wiki/Admin-Manage-Connections/)

<br>

---

<br>

### Como utilizar o GetPass para recuperar as senhas e demais dados das conexões:

<br>

1º Compile o programa:
```
javac GetPassDBeaver.java
```

2º Execute o programa informando o local do arquivo credentials-config.json
```
java GetPassDBeaver ~/.local/share/DBeaverData/workspace6/General/.dbeaver/credentials-config.json
```