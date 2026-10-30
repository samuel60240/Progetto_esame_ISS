# Introduzione

Il seguente manuale serve a condurre una serena e pacifica collaborazione tra colleghi.
Nel seguente testo troverete istruzioni per caricare e scaricare i lavori da GitHub, ordinare i vostri lavori e orientrarvi tra i lavori altrui.

**Promemoria**: ogni volta che effetuate un nuovo lavoro assicuratevi di controllare lo stato dei commit del progetto,
i quali verranno effettuati in diverse fascie orarie da i vari collaboratori. E' buona abitudine effettuare dei commit-push
almeno ogni mezz'ora/ora di lavoro, per evitare perdita di dati per varia natura.

Quando effettuate la vostra prima esperienza su GitHub è normale avere voglia di non farlo, ma vi assicuro che vi sara utile per il vostro futuro, e soprattutto
vi sembrera molto più facile e veloce in seguito.

Prima cosa da fare è copiare il link del progetto, di cui siete stati resi collaboratori, in un area del computer a vostra scelta. (mantenetevi ordinati)
La procedura è la seguente:

## Collegare il tuo PC con il tuo account GitHub

- Bisogna impostare una **chiave di sicurezza** per i push, questa pocedura è difficile ma si effettua una sola volta.
    - Windows:
        - Aprire powershell e digitare il comando:
            
            ```powershell
            ssh-keygen -t ed25519 -C "tua_email@DiGitHub.com"
            ``` 
            
            non dimenticare le virgolette,premuto invio (FARE ATTENZIONE QUANDO RICHIEDE LA FRASE,
            quest'ultima sara richiesta ad ogni push) e risposto alle domande sempre con invio verranno 
            creati 2 file in un percorso specifico che non ti importa andare a scoprire, i due file sono,
            1 la chiave privata, 
            2 la chiave pubblica.
            Non ti resta che copiare la chiave pubblica con il seguente comando:
                
            ```powershell
            Get-Content $env:USERPROFILE\.ssh\id_ed25519.pub | Set-Clipboard
            ```

    - Linux:
        - Aprire il terminale e digitare il comando:
                
            ```bash
            ssh-keygen -t ed25519 -C "tua_email@example.com"
            ```

        - Mostrare la chiave per copiarla col comando:
                
            ```bash
            cat ~/.ssh/id_ed25519.pub
            ```
        
    - MacOS
        - Aprire il terminale e digitare il comando: 

            ```bash
            ssh-keygen -t ed25519 -C "tua_email@example.com"
            ```

        - Copiare la chiave nella clipboard:

            ```bash
            pbcopy < ~/.ssh/id_ed25519.pub
            ```

    - A questo punto avrai la chiave nei tuoi appunti, con la quale collegheremo il tuo dispositivo e il tuo account GitHub.
            Vai su GitHub, Account -> Settings -> SSh and GPG keys, clicca su new SSH key ed incolla CTRL + V

    ## Installare git
    - Installare git, componente fondamentale
        - Windows:
            ```powershell
            winget install --id Git.Git -e --source winget```

        - Linux:
            - Ubuntu / Debian / Mint
                ```bash
                sudo apt update
                sudo apt install git
                ```

            -  Fedora
                ```bash
                sudo dnf install git
                ```

            - Arch / Manjaro
                ```bash
                sudo pacman -S git
                ```
        - MacOs:
            ```bash
            brew install --cask github
            ```
    - Bisogna inizializzare l'ambiente con le vostre credenziali con i seguenti comandi:
        ```bash
        git config --global user.name "Il Tuo Nome"
        git config --global user.email "tua_email@example.com"
        ```

Siamo pronti per iniziare la nostra esperienza di collaborazione su GitHub!!

## Installare questo progetto e usare Git
Come si scarica il progetto sul computer? e perche lo devo fare così spesso?

La prima cosa da fare è collegare il progetto su GitHub in una locazione del vostro computer. Assicuratevi di essere connessi alla rete.
Aprite GitHub, entrate nella repositori di interesse, premete il tasto vere code.

Abbiamo creto una chiave SSH, quindi potete selezionare SSH anziche HTTPS, copiate l' URL.
Fatto cio andate nella vostra area di lavoro sul vostro PC, aprite il terminale e digitate il comando
    
```bash
git clone git@github.com:samuel60240/Progetto_esame_ISS.git
```


A questo punto avrete nella vostra cartella tutto il repositori aggiornato.
Ogni volta che passano più di 12 ore ricordate di fare la pull request, qualcuno potrebbe aver apportato modifiche o aggiornamenti, verificate se state lavorando alla stessa cosa o tenetevi aggiornati.
Il bello di git è che è possibile ritornare alle vecchie versioni anche dopo tempo, per questo è importante usarlo.
Aprite un file nella vostra area di lavoro nel progetto, lavorate per un po' sostanzialmente avete creato qualcosa che non c'era o ne avete alterato l'origine, dovete quindi rendere concrete queste modifiche , effettuate la seguente prassi

```bash
git pull # scarica sul tuo PC tutte le modifiche fatte dai tuoi colleghi nel frattempo. Aggiorna la versione più recente della branca su cui stai lavorando. 

#ATTENZIONE! Se nel frattempo hai fatto modifiche al codice, e non le hai salvato in uno snapshow (commit) saranno perse!
```

```bash
git add <file> # aggiungere un file al controllo delle versioni di git
```

```bash
git add . # aggiungere tutti i file della direttoria corrente al controllo delle versioni di git.
```
       
```bash
git commit -m "messaggio con cui verra salvata la modifica" # che sia significativo il messaggio 
```

```bash
git push # invia le tue modifiche al GitHub
```





