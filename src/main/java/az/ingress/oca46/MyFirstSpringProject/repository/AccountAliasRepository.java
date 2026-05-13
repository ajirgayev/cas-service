package az.ingress.oca46.MyFirstSpringProject.repository;

import az.ingress.oca46.MyFirstSpringProject.entity.AccountAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountAliasRepository extends JpaRepository<AccountAlias, Long> {

    Optional<AccountAlias> findByAlias_AliasIdAndAccount_AccountId(long aliasId, long accountId);
    // Alias-a link olmuş bütün account-lar
    List<AccountAlias> findByAlias_AliasId(long aliasId);


//    @Query("SELECT aa FROM AliasAccount aa " +
//            "JOIN aa.alias a " +
//            "JOIN aa.account ac " +
//            "WHERE a.aliasId =:aliasId " +
//            "AND ac.accountId =:accountId")
//    Optional<AccountAlias> findByAlias_AliasIdAndAccount_AccountId_query(
//            @Param("aliasId") long aliasId,
//            @Param("accountId") long accountId);

    // Alias + Account cütlüyü mövcuddurmu?

    // Alias üçün default olan əlaqə
    Optional<AccountAlias> findByAlias_AliasIdAndIsDefaultTrue(long aliasId);

    // Alias-a link olan account sayı
    long countByAlias_AliasId(long aliasId);
}
