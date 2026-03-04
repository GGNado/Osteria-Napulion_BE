# GIT_WORKFLOW_BACKEND.md
Guida rapida ai comandi Git per il repo Backend (branching + flusso di lavoro) – ottimizzato per GitHub Actions + Docker.

## Branch usati
- `main`    = stabile / produzione (prod-ready)
- `develop` = integrazione continua / dev-staging
- `feature/*` = lavoro su singole funzionalità
- `fix/*`   = bugfix su `develop`
- `hotfix/*` = fix urgente su `main` (solo se necessario)

---

## 0) Setup iniziale (una sola volta): creare `develop` da `main`

### Comandi
```bash
git checkout main
git pull origin main
git checkout -b develop
git push -u origin develop
```

### Perché (in breve)
- Parti dall’ultimo `main` remoto.
- Crei `develop` con la stessa base.
- Pubblicando `develop`, Actions può distinguere DEV (develop) e PROD (main).

---

## 1) Iniziare una nuova feature (sempre)

### Comandi
```bash
git checkout develop
git pull origin develop
git checkout -b feature/nome-feature
```

### Perché
- `develop` è la base di integrazione.
- `feature/...` isola il lavoro e rende più sicura la CI (Actions su PR).

Esempi nomi:
- `feature/auth-jwt`
- `feature/scadenze-api`
- `feature/upload-immagini`

---

## 2) Lavorare durante la feature (loop quotidiano)

### Stato / diff
```bash
git status
git diff
```

### Stage / commit
```bash
git add .
git commit -m "feat: aggiunto endpoint scadenze"
```

### Push
```bash
git push
# (la prima volta su un branch nuovo)
git push -u origin feature/nome-feature
```

### Perché
- commit piccoli = rollback più semplice
- push frequenti = backup + Actions su PR

---

## 3) Aggiornare la tua feature con le ultime modifiche di `develop`

### Opzione A (semplice): merge `develop` dentro la feature
```bash
git checkout develop
git pull origin develop

git checkout feature/nome-feature
git merge develop
```

### Perché
- Ti allinei con ciò che è entrato in `develop` senza riscrivere la storia.
- Riduce sorprese quando poi fai merge/PR.

> Se conflitti:
```bash
# risolvi conflitti nei file
git add .
git commit -m "merge develop into feature/nome-feature"
```

---

## 4) Chiudere la feature: integrare in `develop`

### Consigliato: PR su GitHub (Actions gira su PR)
```bash
git push
```
Poi su GitHub: Pull Request `feature/nome-feature` → `develop`.

### Alternativa: merge locale
```bash
git checkout develop
git pull origin develop
git merge feature/nome-feature
git push origin develop
```

### Perché
- `develop` raccoglie feature finite e testate.
- Le PR fanno girare CI prima del merge (molto utile con Docker build/test).

---

## 5) Rilasciare: portare `develop` su `main`

### Consigliato: PR `develop` → `main`
Su GitHub: Pull Request `develop` → `main`.

### Alternativa: merge locale
```bash
git checkout main
git pull origin main
git merge develop
git push origin main
```

### Perché
- `main` resta sempre “deployabile”.
- Le Actions su `main` possono fare deploy PROD (Docker pull + restart).

---

## 6) Bugfix e hotfix (quando usarli)

### `fix/*` (bug su develop)
Quando trovi un bug mentre sei in fase dev:
```bash
git checkout develop
git pull origin develop
git checkout -b fix/descrizione-bug
# fix...
git add .
git commit -m "fix: descrizione"
git push -u origin fix/descrizione-bug
```
Poi PR `fix/...` → `develop`.

**Perché**: mantieni il flusso normale, CI su PR, niente rischi su main.

### `hotfix/*` (bug urgente in produzione)
Solo se `main` è già in prod e devi sistemare subito:
```bash
git checkout main
git pull origin main
git checkout -b hotfix/descrizione
# fix...
git add .
git commit -m "hotfix: descrizione"
git push -u origin hotfix/descrizione
```
Poi PR `hotfix/...` → `main` (deploy prod).

Dopo il merge su main, riportalo anche su develop:
```bash
git checkout develop
git pull origin develop
git merge main
git push origin develop
```

**Perché**: eviti che develop resti “indietro” rispetto a main.

---

## 7) Cancellare branch feature/fix/hotfix (quando finita)

### Locale
```bash
git branch -d feature/nome-feature
```

### Remoto
```bash
git push origin --delete feature/nome-feature
```

---

## 8) Se per sbaglio hai lavorato su `main` o `develop` 😅

### Caso: commit su `develop` ma volevi una feature
Crea un branch dalla situazione attuale:
```bash
git checkout develop
git checkout -b feature/recupero-lavoro
git push -u origin feature/recupero-lavoro
```
Poi ripulisci `develop` **solo se non hai pushato** (altrimenti chiedimi la procedura sicura).

---

## 9) Comandi utili extra

### Vedere branch e dove sei
```bash
git branch
```

### Log compatto e grafico
```bash
git log --oneline --graph --decorate --all
```

### Stash (salva lavoro non committato)
```bash
git stash
git stash pop
```

---

## Regole d’oro (riassunto)
1) Non lavorare mai direttamente su `main`.
2) Parti sempre da `develop` aggiornato.
3) Ogni task = un branch `feature/...` (o `fix/...`).
4) Integra su `develop` (meglio via PR).
5) Rilascia su `main` (meglio via PR).
6) `hotfix/*` solo per emergenze in produzione, poi riallinea `develop`.
