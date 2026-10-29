# Documento dei Requisiti di Progetto: Red Echoes

Questo documento definisce i **Requisiti Funzionali** e la classificazione completa dei **Requisiti Non Funzionali** per il videogioco d'avventura e investigazione 3D **Red Echoes**, seguendo la gerarchia standard dell'ingegneria del software.

---

## 1. Requisiti Funzionali (Functional Requirements)

I requisiti funzionali definiscono i comportamenti, le azioni e le funzionalità che il sistema deve eseguire:

* **RF-01 (Esplorazione e Navigazione):** Il sistema deve consentire al giocatore di muovere l'astronauta all'interno dell'ambiente 3D di Kepler-186f, gestendo collisioni con il terreno, la vegetazione e gli ostacoli.
* **RF-02 (Raccolta Indizi e Oggetti):** Il giocatore deve poter interagire con elementi dello scenario per raccogliere registrazioni audio, documenti di testo e log della spedizione precedente.
* **RF-03 (Strumenti Scientifici):** Il sistema deve fornire al giocatore strumenti per scansionare l'ambiente, analizzare la vegetazione rossastra e rilevare anomalie o segnali.
* **RF-04 (Risoluzione Enigmi):** Il gioco deve verificare lo stato di avanzamento degli enigmi ambientali basandosi sulle informazioni e sugli oggetti raccolti.
* **RF-05 (Bifurcazione Narrativa e Finali):** Il sistema deve tracciare le decisioni chiave prese dal giocatore e sbloccare uno dei **due finali previsti** in base alle scelte effettuate nell'ultima fase di gioco.
* **RF-06 (Gestione Salvataggi):** Il gioco deve permettere il salvataggio manuale e automatico dello stato del giocatore (posizione, inventario, indizi trovati).

---

## 2. Requisiti Non Funzionali (Non-Functional Requirements)

```
                       ┌─────────────────────────┐
                       │ Requisiti non funzionali│
                       └────────────┬────────────┘
         ┌──────────────────────────┼──────────────────────────┐
┌────────┴────────┐        ┌────────┴────────┐        ┌────────┴────────┐
│  Requisiti del  │        │    Requisiti    │        │    Requisiti    │
│    prodotto     │        │ organizzativi   │        │     esterni     │
└────────┬────────┘        └────────┬────────┘        └────────┬────────┘
```

---

### 2.1 Requisiti del Prodotto

#### A. Requisiti di Efficienza
* **Requisiti di Prestazioni:**
  * Il gioco deve mantenere un framerate stabile di almeno **60 FPS** a risoluzione 1080p su configurazioni hardware medie.
  * Il tempo di caricamento iniziale della mappa non deve superare i **10 secondi**.
* **Requisiti di Spazio:**
  * La dimensione totale dell'installazione su disco non deve superare i **15 GB**.
  * L'occupazione di memoria RAM deve rimanere entro un limite di **8 GB** durante l'esecuzione standard.

#### B. Requisiti di Usabilità
* L'interfaccia utente (HUD) deve essere minimalista per favorire l'immersione nell'ambiente alieno.
* I comandi di gioco devono essere configurabili sia per tastiera/mouse che per controller (gamepad).
* L'interfaccia deve supportare testi e sottotitoli in lingua italiana e inglese.

#### C. Requisiti di Affidabilità
* Il tasso di crash del sistema deve essere inferiore a **1 ogni 50 ore** di gioco continuo.
* In caso di chiusura improvvisa, il sistema deve ripristinare l'ultimo checkpoint valido senza corruzione del salvataggio.

#### D. Requisiti di Protezione
* I file di salvataggio locali devono essere validati tramite checksum per prevenire la modifica o corruzione intenzionale dei dati di gioco.

---

### 2.2 Requisiti Organizzativi

#### A. Requisiti Ambientali
* Gli asset 3D e i moduli di terreno sviluppati in Blender devono essere ottimizzati per l'efficienza energetica della GPU/CPU durante il rendering in tempo reale.

#### B. Requisiti di Funzionamento
* Il gioco deve poter funzionare in modalità completamente **offline** senza richiedere una connessione internet costante.

#### C. Requisiti di Sviluppo
* **Pipeline grafica:** Utilizzo di Blender per la modellazione low-poly/mid-poly e il texturing dei moduli di terreno.
* **Modularity:** Architettura dei livelli basata su tessere modulari intercambiabili (Tiles 10x10) con punti di connessione predefiniti.
* **Codice:** Implementazione delle componenti di rete e script logici secondo standard documentati e puliti.

---

### 2.3 Requisiti Esterni

#### A. Requisiti Normativi
* **Requisiti Contabili:** Il progetto deve rientrare nei costi di licenza previsti per tool open-source e software gratuito (Blender, librerie Python).
* **Requisiti di Sicurezza / Protezione:** Conformità con le linee guida della piattaforma di distribuzione (es. Steam / Epic Games Store) relative all'esecuzione sicura di software su PC.

#### B. Requisiti Etici
* Trattamento di temi quali la responsabilità della ricerca scientifica e l'esplorazione spaziale senza contenuti espliciti gratuiti, mantenendo una classificazione PEGI 12+.

#### C. Requisiti Legislativi
* Rispetto delle licenze d'uso delle librerie terze utilizzate nel progetto (GPL/MIT).
* Conformità al GDPR per l'eventuale raccolta anonima di dati di crash o telemetria di gioco.

---
*Documento di Analisi dei Requisiti per Red Echoes.*