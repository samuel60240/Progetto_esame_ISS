Il seguente manuale serve a condurre una serena e pacifica collaborazione tra colleghi.
Nel seguente testo troverete istruzioni per caricare e scaricare i lavori da git hub, ordinare i vostri lavori e orientrarvi tra i lavori altrui.

Promemoria, ogni volta che effetuate un nuovo lavoro assicuratevi di controllare lo stato dei commit del progetto,
i quali verranno effettuati in diverse fascie orarie da i vari collaboratori. E' buona abitudine effettuare dei commit-push
almeno ogni mezz'ora/ora di lavoro, per evitare perdita di dati per varia natura.

Quando effettuate la vostra prima esperienza su git hub è normale avere voglia di non farlo, ma vi assicuro che vi sara utile per il vostro futuro, e soprattutto
vi sembrera molto più facile e veloce in seguito.
Prima cosa da fare è copiare il link del progetto, di cui siete stati resi collaboratori, in un area del computer a vostra scelta.(mantenetevi ordinati)
La procedura è la seguente:

    -Bisogna impostare una chiave per i push, questa pocedura è difficile ma si effettua una sola volta.
        windows:
            -Aprire powershell e digitare il comando:
            
                ssh-keygen -t ed25519 -C "tua_email@DiGitHub.com" 
            
            non dimenticare le virgolette,premuto invio (FARE ATTENZU^IONE QUANDO RICHIEDE LA FRASE,
            quest'ultima sara richiesta ad ogni push) e risposto alle domande sempre con invio verranno 
            creati 2 file in un percorso specifico che non ti importa andare a scoprire, i due file sono,
            1 la chiave privata, 
            2 la chiave pubblica.
            Non ti resta che copiare la chiave pubblica con il seguente comando:
                
                Get-Content $env:USERPROFILE\.ssh\id_ed25519.pub | Set-Clipboard
            
            A questo punto avrai la chiave nei tuoi appunti, vai su git hub, Account-> SSh and GPG keys, clicca su new SSH key ed incolla CTRL + V

        linux:
            -Aprire il terminale e digitare il comando:
                
                ssh-keygen -t ed25519 -C "tua_email@example.com"

            -Mostrare la chiave per copiarla col comando:
                
                cat ~/.ssh/id_ed25519.pub

            -Vai su git hub Account->SSH and GPG keys, clicca su new SSH key ed incolla la chiave

        MacOS
            
            ssh-keygen -t ed25519 -C "tua_email@example.com"

            pbcopy < ~/.ssh/id_ed25519.pub


    -Installare git, componente fondamentale
        windows:
            winget install --id Git.Git -e --source winget

        linux:
            # Ubuntu / Debian / Mint
                sudo apt update
                sudo apt install git

            # Fedora
                sudo dnf install git

            # Arch / Manjaro
                sudo pacman -S git

    -Bisogna inizializzare l'ambiente con le vostre credenziali con i seguenti comandi:
        
        git config --global user.name "Il Tuo Nome"
        git config --global user.email "tua_email@example.com"

Siamo pronti per iniziare la nostra esperienza di collaborazione su git hub!!

Come si scarica il progetto sul computer? e perche lo devo fare così spesso?

La prima cosa da fare è collegare il prgetto su git hub in una locazione del vostro computer. Assicuratevi di essere connessi alla rete.
Aprite Git hub, entrate nella repositori di interesse, premente il tasto vere code.

Abbiamo creto una chiave SSH, quindi potete selezionare SSH anziche HTTPS, copiate l' URL.
Fatto cio andate nella vostra area di lavoro sul vostro PC, aprite il terminale e digitate il comando
    
    git clone git@github.com:samuel60240/Progetto_esame_ISS.git


A questo punto avrete nella vostra cartella tutto il repositori aggiornato.
Ogni volta che passano più di 12 ora ricordate di fare la pull request, qualcuno potrebbe aver apportato modifiche o aggiornamenti,
verificate se state lavorando alla stessa cosa o tenetevi aggiornati.
Il bello di git è che è possibile ritornare alle vecchie versioni anche dopo tempo, per questo è importante usarlo.
Aprite un file nella vostra are di lavoro nel progetto, lavorate per un po' sostanzialmente avete creato qualcosa che non c'era o ne avete alterato
l'origine, dovete quindi rendere concrete queste modifiche , effettuate la seguente prassi

       git  add (elenco ogni elemento che voglio aggiungere all' elenco dei file podifcati
       git add . #seleziona tutti i file, attenzione pero! se avevate versioni precedenti, 
                    e non effettuate l'aggiornamento al repositori rompete tutto
       git pull #per evitare incompatibilità con la versione su git hub prima di effettuare add, commit e push
       git commit -m "messaggio con cui verra salvata la modifica" # che sia significativo il messaggio
       git push



